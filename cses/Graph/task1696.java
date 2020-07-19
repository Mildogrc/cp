import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class task1696 {

	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int M = fi.nextInt();
		int K = fi.nextInt();
		Flow net = new Flow(N + M + 2, N + M + K);
		int s = 0;
		int t = N + M + 1;
		for (int i = 1; i <= N; i++)
			net.addEdge(s, i, 1);
		for (int i = N + 1; i < t; i++)
			net.addEdge(i, t, 1);
		Set<Pair<Integer, Integer>> edges = new HashSet<>();
		for (int i = 0; i < K; i++) {
			int u = fi.nextInt();
			int v = fi.nextInt();
			if (edges.add(mp(u, v)))
				net.addEdge(u, v + N, 1);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(net.edmondsKarp(s, t) + "\n");
		for (Pair<Integer, Integer> e : edges) {
			if (net.c[e.f][e.s + N] == 0)
				sb.append(e);
		}
		System.out.println(sb);
	}

	static <F, S> Pair<F, S> mp(F f, S s) {
		return new Pair<F, S>(f, s);
	}

	static class Pair<F, S> {
		F f;
		S s;

		Pair(F f, S s) {
			this.f = f;
			this.s = s;
		}

		@Override
		public int hashCode() {
			int a = f.hashCode();
			int b = f.hashCode();
			return (a << 5) - a + b;
		}

		@Override
		public String toString() {
			return f.toString() + " " + s.toString() + "\n";
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null)
				return false;
			if (o instanceof Pair<?, ?>) {
				Pair<?, ?> p = (Pair<?, ?>) o;
				return p.f.equals(f) && p.s.equals(s);
			}
			return false;
		}

	}

	static class Flow {
		int N;
		List<Integer>[] adj;
		long[][] c;
		List<int[]> edges;

		Flow(int N, int M) {
			this.N = N;
			adj = new List[N];
			for (int i = 0; i < N; i++)
				adj[i] = new ArrayList<>();
			c = new long[N][N];
			edges = new ArrayList<>();
		}

		void addEdge(int u, int v, int capacity) {
			if (c[u][v] == 0 && c[v][u] == 0) {
				adj[u].add(v);
				adj[v].add(u);
			}
			c[u][v] += capacity;
			edges.add(new int[] { u, v, capacity });
			edges.add(new int[] { v, u, capacity });
		}

		long fordFulkerson(int src, int sink) {
			long maxFlow = 0;
			while (true) {
				int[] pred = DFS(src, sink);
				if (pred[sink] == -1) {
					return maxFlow;
				}
				long min = getMin(sink, pred);
				augment(min, sink, pred);
				maxFlow += min;
			}
		}

		long edmondsKarp(int src, int sink) {
			long maxFlow = 0;
			while (true) {
				int[] pred = BFS(src, sink);
				if (pred[sink] == -1) {
					return maxFlow;
				}
				long min = getMin(sink, pred);
				augment(min, sink, pred);
				maxFlow += min;
			}
		}

		List<int[]> minCut(int src, int sink) {
			edmondsKarp(src, sink);
			boolean[] visited = checkMinCut(src);
			List<int[]> mincut = new ArrayList<>();
			for (int[] e : edges) {
				if (visited[e[0]] && !visited[e[1]]) {
					mincut.add(e);
				}
			}
			return mincut;
		}

		private int[] DFS(int src, int sink) {
			boolean[] visited = new boolean[N];
			int[] pred = new int[N];
			Arrays.fill(visited, false);
			Arrays.fill(pred, -1);
			visited[src] = true;
			pred[src] = src;
			Stack<Integer> dfs = new Stack<>();
			dfs.add(0);
			while (dfs.size() > 0) {
				int u = dfs.pop();
				if (u == sink)
					break;
				for (int v : adj[u]) {
					if (!visited[v] && c[u][v] != 0) {
						visited[v] = true;
						pred[v] = u;
						dfs.add(v);
					}
				}
			}
			return pred;
		}

		private int[] BFS(int src, int sink) {
			boolean[] visited = new boolean[N];
			int[] pred = new int[N];
			Arrays.fill(pred, -1);
			visited[src] = true;
			pred[src] = src;
			Queue<Integer> dfs = new LinkedList<>();
			dfs.add(0);
			while (dfs.size() > 0) {
				int u = dfs.poll();
				if (u == sink)
					break;
				for (int v : adj[u]) {
					if (!visited[v] && c[u][v] > 0) {
						visited[v] = true;
						pred[v] = u;
						dfs.add(v);
					}
				}
			}
			return pred;
		}

		private boolean[] checkMinCut(int src) {
			Queue<Integer> bfs = new LinkedList<>();
			boolean[] visited = new boolean[N];
			bfs.add(src);
			visited[src] = true;
			while (bfs.size() > 0) {
				int u = bfs.poll();
				for (int v : adj[u]) {
					if (!visited[v] && c[u][v] > 0) {
						visited[v] = true;
						bfs.add(v);
					}
				}
			}
			return visited;
		}

		private long getMin(int sink, int[] pred) {
			int node = sink;
			long min = Long.MAX_VALUE;
			while (pred[node] != node) {
				min = Math.min(min, c[pred[node]][node]);
				node = pred[node];
			}
			return min;
		}

		private void augment(long min, int sink, int[] pred) {
			int node = sink;
			while (pred[node] != node) {
				int parent = pred[node];
				c[parent][node] -= min;
				c[node][parent] += min;
				node = parent;
			}
		}
	}

	static class FastInput {
		final private int BUFFER_SIZE = 1 << 16;
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
