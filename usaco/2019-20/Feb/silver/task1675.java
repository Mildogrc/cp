import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class task1675 {
	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int M = fi.nextInt();

		Edge[] edges = new Edge[M];

		for (int i = 0; i < M; i++)
			edges[i] = new Edge(fi.nextInt() - 1, fi.nextInt() - 1, fi.nextLong());

		Arrays.sort(edges, (a, b) -> Long.compare(a.w, b.w));

		UnionFind uf = new UnionFind(N);

		long cost = 0;
		int count = 0;
		for (Edge e : edges) {
			if (uf.union(e.u, e.v)) {
				cost += e.w;
				if (++count == N - 1) {
					System.out.println(cost);
					return;
				}
			}
		}
		System.out.println("IMPOSSIBLE");
	}

	static class UnionFind {
		int[] parent;

		UnionFind(int N) {
			parent = new int[N];
			Arrays.fill(parent, -1);
		}

		int find(int x) {
			int lead = x;
			while (parent[lead] >= 0) {
				lead = parent[lead];
			}
			while (x != lead) {
				int nxt = parent[x];
				parent[x] = lead;
				x = nxt;
			}
			return lead;
		}

		boolean union(int a, int b) {
			a = find(a);
			b = find(b);
			if (a == b) {
				return false;
			} else {
				if (parent[a] < parent[b]) {
					parent[a] += parent[b];
					parent[b] = a;
				} else {
					parent[b] += parent[a];
					parent[a] = b;
				}
				return true;
			}
		}
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
