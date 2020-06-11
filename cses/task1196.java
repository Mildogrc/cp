import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class task1196 {
	static final long INF = (long) 1e18;

	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int M = fi.nextInt();
		int K = fi.nextInt();

		List<Edge>[] adj = new List[N];
		long[] dist = new long[N];
		int[] count = new int[N];

		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < M; i++)
			adj[fi.nextInt() - 1].add(new Edge(fi.nextInt() - 1, fi.nextLong()));

		List<Long> paths = new ArrayList<>();

		Queue<Path> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
		pq.add(new Path(0, 0));

		while (pq.size() > 0) {
			Path p = pq.poll();
			count[p.node]++;
			if (p.node == N - 1) {
				paths.add(p.dist);
				if (paths.size() == K) {
					StringBuilder sb = new StringBuilder(K * 20);
					for (int i = 0; i < K; i++)
						sb.append(paths.get(i) + " ");
					System.out.println(sb);
					return;
				}
			}
			if (count[p.node] <= K) {
				for (Edge e : adj[p.node]) {
					pq.add(new Path(e.v, p.dist + e.w));
				}
			}
		}

		StringBuilder sb = new StringBuilder(K * 20);
		for (int i = 0; i < K; i++)
			sb.append(paths.get(i) + " ");
		System.out.println(sb);

	}

	static class Path {
		int node;
		long dist;

		Path(int node, long dist) {
			this.node = node;
			this.dist = dist;
		}
	}

	static class Edge {
		int v;
		long w;

		Edge(int dest, long weight) {
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
