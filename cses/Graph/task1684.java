import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
 
public class task1684 {
	static int N;
 
	public static void main(String[] args) throws IOException {
		FastInput in = new FastInput();
		int M = in.nextInt();
		N = in.nextInt();
		Graph g = new Graph(N << 1);
		for (int i = 0; i < M; i++) {
			int x1 = ((in.nextString().equals("+")) ? in.nextInt() : in.nextInt() + N) - 1;
			int x2 = ((in.nextString().equals("+")) ? in.nextInt() : in.nextInt() + N) - 1;
			g.addEdge(not(x1), x2, false);
			g.addEdge(not(x2), x1, false);
		}
		List<List<Integer>> SCCs = g.Kosaraju();
		Collections.reverse(SCCs);
		int[] comp = new int[N << 1];
		for (int i = 0; i < SCCs.size(); i++)
			for (int u : SCCs.get(i))
				comp[u] = i;
		boolean[] topping = new boolean[N];
		for (int i = 0; i < N; i++)
			topping[i] = comp[i] == comp[not(i)] ? IMPOSSIBLE() : (comp[i] > comp[not(i)]);
		StringBuilder sb = new StringBuilder();
		for (boolean b : topping)
			sb.append(b ? '+' : '-').append(' ');
		System.out.println(sb);
	}
 
	static boolean IMPOSSIBLE() {
		System.out.println("IMPOSSIBLE");
		System.exit(0);
		return false;
	}
 
	static int not(int x) {
		return x < N ? x + N : x - N;
	}
 
//TEMPLATE CODE
	static class Graph {
		int N;
		List<Integer>[] adj;
		List<Edge> edges;
		static final long INF = (long) 1e18;
 
		static class Edge {
			int u;
			int v;
 
			Edge(int src, int dest) {
				u = src;
				v = dest;
			}
		}
 
		Graph(int V) {
			this.N = V;
			adj = new List[V];
			for (int i = 0; i < V; i++) {
				adj[i] = new ArrayList<>();
			}
			edges = new ArrayList<>();
		}
 
		void addEdge(int src, int dest, boolean bidi) {
			this.adj[src].add(dest);
			if (bidi)
				this.adj[dest].add(src);
			edges.add(new Edge(src, dest));
		}
 
		static class Path {
			long[] dist;
			int[] pred;
 
			Path(int[] pred, long[] dist) {
				this.pred = pred;
				this.dist = dist;
			}
 
			long getDist(int dest) {
				return dist[dest];
			}
 
			int[] getPath(int dest) {
				if (dist[dest] == INF)
					return null;
				List<Integer> path = new ArrayList<>();
				while (dest != -1) {
					path.add(dest);
					dest = pred[dest];
				}
				int[] arr = new int[path.size()];
				for (int i = path.size() - 1, j = 0; i >= 0; i--, j++) {
					arr[j] = path.get(i);
				}
				return arr;
			}
		}
 
		Path BFS(int... srcs) {
			Queue<Integer> bfs = new LinkedList<>();
			long[] dist = new long[N];
			int[] pred = new int[N];
			boolean[] visited = new boolean[N];
			for (int src : srcs) {
				bfs.add(src);
				dist[src] = 0;
				pred[src] = -1;
				visited[src] = true;
			}
			while (bfs.size() > 0) {
				int sz = bfs.size();
				while (sz-- > 0) {
					int node = bfs.poll();
					for (int child : adj[node]) {
						if (!visited[child]) {
							bfs.add(child);
							pred[child] = node;
							dist[child] = dist[node] + 1;
							visited[child] = true;
						}
					}
				}
			}
			return new Path(pred, dist);
		}
 
		List<List<Integer>> Kosaraju() {
			int[] map = finishingTimes();
			List<List<Integer>> SCCs = new ArrayList<>();
			boolean[] visited = new boolean[N];
			for (int i = N - 1; i >= 0; i--) {
				if (!visited[map[i]]) {
					List<Integer> SCC = new ArrayList<>();
					Queue<Integer> bfs = new LinkedList<>();
					bfs.add(map[i]);
					SCC.add(map[i]);
					visited[map[i]] = true;
					while (bfs.size() > 0) {
						int sz = bfs.size();
						while (sz-- > 0) {
							int node = bfs.poll();
							for (int child : adj[node]) {
								if (!visited[child]) {
									SCC.add(child);
									bfs.add(child);
									visited[child] = true;
								}
							}
						}
					}
					SCCs.add(SCC);
				}
			}
			return SCCs;
		}
 
		private int[] finishingTimes() {
			List<Integer>[] grev = new List[N];
			for (int i = 0; i < N; i++) {
				grev[i] = new ArrayList<>();
			}
			for (int x = 0; x < N; x++) {
				for (int y : adj[x]) {
					grev[y].add(x);
				}
			}
			int t = 0;
			int[] finish = new int[N];
			Arrays.fill(finish, -1);
			boolean[] visited = new boolean[N];
			for (int i = N - 1; i >= 0; i--) {
				if (!visited[i]) {
					Stack<Integer> dfs = new Stack<Integer>();
					dfs.push(i);
					while (dfs.size() > 0) {
						if (visited[dfs.peek()]) {
							int node = dfs.pop();
							if (finish[node] == -1)
								finish[node] = t++;
						} else {
							visited[dfs.peek()] = true;
							for (int child : grev[dfs.peek()]) {
								if (!visited[child]) {
									dfs.push(child);
								}
							}
						}
					}
				}
			}
			int[] flippedFinish = new int[finish.length];
			for (int i = 0; i < finish.length; i++) {
				flippedFinish[finish[i]] = i;
			}
			return flippedFinish;
		}
 
		List<List<Integer>> tarjan() {
			return null;
		}
 
		int[] topologicalSort(boolean mapping) {
			int[] indeg = new int[N];
			int[] order = new int[N];
			int t = 0;
			Arrays.fill(order, -1);
			for (int u = 0; u < N; u++) {
				for (int v : adj[u]) {
					indeg[v]++;
				}
			}
			Queue<Integer> queue = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				if (indeg[i] == 0)
					queue.add(i);
			}
			for (int i = 0; i < N; i++) {
				if (queue.isEmpty()) {
					return null;
				}
				int u = queue.poll();
				if (mapping)
					order[u] = t++;
				else
					order[t++] = u;
				for (int v : adj[u]) {
					if (--indeg[v] == 0) {
						queue.add(v);
					}
				}
			}
			for (int i = 0; i < N; i++) {
				if (order[i] == -1)
					return null;
			}
			return order;
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
 
		public String nextString() throws IOException {
			StringBuilder buf = new StringBuilder(64);
			byte c = read();
			while (c == ' ' || c == '\n')
				c = read();
			do {
				buf.append((char) c);
			} while ((c = read()) != ' ' && c != '\n');
			return buf.toString().trim();
		}
 
		public String readLine() throws IOException {
			StringBuilder buf = new StringBuilder(64);
			byte c = read();
			while (c <= ' ')
				c = read();
			do {
				buf.append((char) c);
			} while ((c = read()) != '\n');
			return buf.toString().trim();
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
