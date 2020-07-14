import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TEST {
	static final long INF = (long) 1e18;

	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int M = fi.nextInt();

		List<Edge>[] g = new List[N];
		List<Edge>[] grev = new List[N];

		for (int i = 0; i < N; i++) {
			g[i] = new ArrayList<>();
			grev[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int u = fi.nextInt() - 1;
			int v = fi.nextInt() - 1;
			long w = fi.nextLong();
			g[u].add(new Edge(v, w));
			grev[v].add(new Edge(u, w));
		}

		long[] dist0 = Dijkstra(0, g);
		long[] distN = Dijkstra(N - 1, grev);
		long min = dist0[N - 1];
		for (int i = 0; i < N; i++) {
			for (Edge e : g[i]) {
				min = Math.min(min, dist0[i] + (e.w >> 1) + distN[e.v]);
			}
		}
		System.out.println(min);
	}

	public static long[] Dijkstra(int src, List<Edge>[] adj) {
		long[] dist = new long[adj.length];
		Arrays.fill(dist, INF);
		dist[src] = 0;
		Queue<Integer> pq = new PriorityQueue<>((a, b) -> Long.compare(dist[a], dist[b]));
		pq.add(src);
		boolean[] visited = new boolean[adj.length];
		while (pq.size() > 0) {
			int u = pq.poll();
			if (visited[u])
				continue;
			for (Edge e : adj[u]) {
				if (!visited[u] && dist[e.v] > dist[u] + e.w) {
					dist[e.v] = dist[u] + e.w;
					pq.add(e.v);
				}
			}
			visited[u] = true;
		}
		return dist;
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
