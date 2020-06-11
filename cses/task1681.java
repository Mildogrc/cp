import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class task1681 {
	static final long INF = (long) 1e18;
	static final int MOD = (int) 1e9 + 7;

	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int M = fi.nextInt();

		List<Integer>[] adj = new List[N];
		Queue<Integer> topo = new LinkedList<>();
		int[] count = new int[N];
		int[] in = new int[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		HashSet<String> nodes = new HashSet<>();
		for (int i = 0; i < M; i++) {
			int u = fi.nextInt() - 1;
			int v = fi.nextInt() - 1;
			if (nodes.add(u + " " + v)) {
				in[v]++;
				adj[u].add(v);
			}
		}

		for (int i = 1; i < N; i++) {
			if (in[i] == 0)
				topo.add(i);
		}
		count[0] = 1;

		while (topo.size() > 0)
			for (int v : adj[topo.poll()])
				if (--in[v] == 0)
					topo.add(v);
		topo.add(0);

		while (topo.size() > 0) {
			int u = topo.poll();
			for (int v : adj[u]) {
				count[v] = (count[v] + count[u]) % MOD;
				if (--in[v] == 0)
					topo.add(v);
			}
		}
		System.out.println(count[N - 1]);
	}

	public static String rev(int x) {
		return new StringBuilder(String.valueOf(x)).reverse().toString();
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
