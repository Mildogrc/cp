import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class task1197 {
	static final long INF = (long) 1e18;

	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int M = fi.nextInt();

		Edge[] edges = new Edge[M];

		for (int i = 0; i < M; i++) {
			edges[i] = new Edge(fi.nextInt() - 1, fi.nextInt() - 1, fi.nextLong());
		}

		long[] dist = new long[N];
		int[] pred = new int[N];
		Arrays.fill(dist, -INF);
		dist[0] = 0;
		boolean done = true;
		for (int i = 0; i < N; i++) {
			done = true;
			for (Edge e : edges) {
				if (dist[e.u] != INF && dist[e.v] > dist[e.u] + e.w) {
					dist[e.v] = dist[e.u] + e.w;
					pred[e.v] = e.u;
					done = false;
				}
			}
			if (done)
				break;
		}
		int start = -1;
		for (Edge e : edges) {
			if (dist[e.u] != INF && dist[e.v] > dist[e.u] + e.w) {
				start = e.u;
			}
		}
		if (start == -1) {
			System.out.println("NO");
			return;
		}
		List<Integer> path = new ArrayList<>();
		Set<Integer> used = new HashSet<>();
		while (true) {
			path.add(start);
			if (used.contains(start))
				break;
			used.add(start);
			start = pred[start];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = path.size() - 1; i >= 0; i--) {
			sb.append((path.get(i) + 1) + " ");
			if (!used.contains(path.get(i)))
				break;
			used.remove(path.get(i));
		}
		System.out.println("YES");
		System.out.println(sb);
	}

	public static String reverse(int a) {
		return new StringBuilder(String.valueOf(a + 1)).reverse().toString();
	}

	static class Edge {
		int u;
		int v;
		long w;

		Edge(int src, int dest, long weight) {
			u = src;
			v = dest;
			w = weight;
		}
	}

	static class FastInput {
		final private int BUFFER_SIZE = 1 << 6;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public FastInput() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public FastInput(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

}
