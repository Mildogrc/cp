import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class task1202 {
	static final long INF = (long) 1e18;
	static final int MOD = (int) 1e9 + 7;

	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int M = fi.nextInt();

		List<Edge>[] adj = new List[N];
		int[] min = new int[N];
		int[] max = new int[N];
		int[] count = new int[N];
		long[] dist = new long[N];
		boolean[] visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
			dist[i] = INF;
			min[i] = N;
			max[i] = -1;
		}
		dist[0] = 0;
		min[0] = 0;
		max[0] = 0;
		count[0] = 1;

		for (int i = 0; i < M; i++) {
			int u = fi.nextInt() - 1;
			int v = fi.nextInt() - 1;
			long w = fi.nextLong();
			adj[u].add(new Edge(v, w));
		}

		Queue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
		pq.add(new Node(0, 0));

		while (pq.size() > 0) {
			Node u = pq.poll();
			if (visited[u.u])
				continue;
			for (Edge e : adj[u.u]) {
				if (dist[e.v] == u.dist + e.w) {
					count[e.v] = (count[e.v] + count[u.u]) % MOD;
					min[e.v] = Math.min(min[e.v], min[u.u] + 1);
					max[e.v] = Math.max(max[e.v], max[u.u] + 1);
				} else if (dist[e.v] > u.dist + e.w) {
					dist[e.v] = u.dist + e.w;
					count[e.v] = count[u.u];
					min[e.v] = min[u.u] + 1;
					max[e.v] = max[u.u] + 1;
					pq.add(new Node(e.v, dist[e.v]));
				}
			}
			visited[u.u] = true;
		}

		System.out.printf("%d %d %d %d\n", dist[N - 1], count[N - 1], min[N - 1], max[N - 1]);
	}

	static class Node {
		int u;
		long dist;

		Node(int a, long b) {
			u = a;
			dist = b;
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
