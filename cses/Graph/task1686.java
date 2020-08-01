import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Function;

public class task1686 {
	public static void main(String[] args) throws IOException {
		int N = in.nextInt(), M = in.nextInt();
		int[] arr = new int[N];
		Graph g = new Graph(N);
		for (int i = 0; i < N; i++)
			arr[i] = in.nextInt();

		for (int i = 0; i < M; i++)
			g.addEdge(in.nextInt() - 1, in.nextInt() - 1, false);

		List<List<Integer>> SCCs = g.Kosaraju();
		List<Integer>[] condensed = new List[SCCs.size()];
		for (int i = 0; i < condensed.length; i++)
			condensed[i] = new ArrayList<>();

		int[] map = new int[N];
		for (int i = 0; i < SCCs.size(); i++)
			for (int j : SCCs.get(i))
				map[j] = i;

		for (int i = 0; i < N; i++)
			for (int j : g.adj[i])
				if (map[i] != map[j])
					condensed[map[j]].add(map[i]);

		long[] dp = new long[SCCs.size()];
		for (int i = SCCs.size(); i > 0; i--)
			for (int j : SCCs.get(i - 1))
				dp[i - 1] += arr[j];
		long ans = 0;
		for (int i = SCCs.size() - 1; i >= 0; i--) {
			long max = 0;
			for (int j : condensed[i])
				max = Math.max(max, dp[j]);
			dp[i] += max;
			ans = Math.max(ans, dp[i]);
		}
		out.println(ans);
		out.close();
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

		static int TARJAN_TIMER, TARJAN_NCOMPS;

		List<List<Integer>> tarjan() {
			int[] val = new int[N], comp = new int[N];
			Arrays.fill(comp, -1);
			List<Integer> cur = new ArrayList<>();
			List<List<Integer>> SCC = new ArrayList<>();
			Stack<Integer> z = new Stack<>();
			TARJAN_TIMER = 0;
			TARJAN_NCOMPS = 0;
			Function<Integer, Integer> dfs = new Function<Integer, Integer>() {
				@Override
				public Integer apply(Integer u) {
					int low = val[u] = ++TARJAN_TIMER, v;
					z.push(u);
					for (int a : adj[u])
						if (comp[a] < 0 && val[a] == 0)
							low = Math.min(low, this.apply(a));
					if (low == val[u]) {
						do {
							v = z.pop();
							comp[v] = TARJAN_NCOMPS;
							cur.add(v);
						} while (v != u);
						SCC.add(new ArrayList<>(cur));
						cur.clear();
						++TARJAN_NCOMPS;
					}
					return val[u] = low;
				}
			};
			for (int u = 0; u < N; ++u)
				if (comp[u] < 0)
					dfs.apply(u);
			return SCC;
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

	public static <F, S> Pair<F, S> mp(F f, S s) {
		return new Pair<F, S>(f, s);
	}

	public static FastInput in = new FastInput();
	public static FastOutput out = new FastOutput();

	public static void setIO(String file) {
		try {
			in = new FastInput(file + ".in");
			out = new FastOutput(file + ".out");
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
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
			int a = f == null ? 0 : f.hashCode();
			int b = s == null ? 0 : s.hashCode();
			return (a << 5) - a + b;
		}

		@Override
		public String toString() {
			return String.format("(%s, %s)", f == null ? "null" : f.toString(), s == null ? "null" : s.toString());
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null)
				return false;
			if (o instanceof Pair<?, ?>) {
				Pair<?, ?> p = (Pair<?, ?>) o;
				return ((f == p.f) || p.f.equals(f)) && (s == p.s || p.s.equals(s));
			}
			return false;
		}
	}

	static void print(Object o) {
		if (LOCAL)
			System.out.print(o);
	}

	static void println(Object o) {
		if (LOCAL)
			System.out.println(o);
	}

	static void printf(String format, Object... args) {
		if (LOCAL)
			System.out.printf(format, args);
	}

	static boolean LOCAL;
	static long INF = (long) 1e18;
	static int MOD = (int) 1e9 + 7;
	static {
		LOCAL = new File("dummy").exists();
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

	static class FastOutput {
		StringBuilder cache;
		Writer os;
		int BufferLimit = 1 << 16;

		FastOutput() {
			cache = new StringBuilder();
			os = new OutputStreamWriter(System.out);
		}

		FastOutput(String file) throws IOException {
			cache = new StringBuilder();
			os = new FileWriter(file);
		}

		void print(Object o) {
			cache.append(o.toString());
			if (cache.length() > BufferLimit)
				flush();
		}

		void println(Object o) {
			cache.append(o.toString() + '\n');
			if (cache.length() > BufferLimit)
				flush();
		}

		void println() {
			cache.append("\n");
			if (cache.length() > BufferLimit)
				flush();
		}

		void flush() {
			try {
				os.append(cache);
				os.flush();
				cache.setLength(0);
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}

		public void close() {
			flush();
			try {
				os.close();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}
	}
}

