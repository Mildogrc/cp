import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class CF427C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		Graph g = new Graph(N, M);
		for (int i = 0; i < M; i++) {
			String[] tok = br.readLine().split(" ");
			g.addEdge(Integer.parseInt(tok[0]) - 1, Integer.parseInt(tok[1]) - 1, false);
		}
		List<List<Integer>> SCC = g.Kosaraju();
		Mint prod = new Mint(1);
		long sum = 0;
		for (List<Integer> list : SCC) {
			int min = arr[list.get(0)];
			for (int i = 0; i < list.size(); i++) {
				if (arr[list.get(i)] < min)
					min = arr[list.get(i)];
			}
			int count = 0;
			for (int i = 0; i < list.size(); i++) {
				if (arr[list.get(i)] == min)
					count++;
			}
			sum += min;
			prod.mult(count);
		}
		System.out.println(sum + " " + prod.v);
	}

	static class Mint {
		int v;
		static final int mod = (int) 1e9 + 7;

		Mint(int a) {
			v = a;
		}

		void add(int a) {
			if ((v += a) >= mod)
				v -= mod;
		}

		void sub(int a) {
			if ((v -= a) < 0)
				v += mod;
		}

		void mult(int m) {
			v = (int) (((long) v * (long) m) % mod);
		}

		void div(int m) {
			v /= m;
		}

		void add(Mint m) {
			if ((v += m.v) >= mod)
				v -= mod;
		}

		void sub(Mint m) {
			if ((v -= m.v) < 0)
				v += mod;
		}

		Mint mult(Mint m) {
			return new Mint((int) (((long) v * (long) m.v) % mod));
		}

	}

	static class Graph {
		int N, M;
		List<Integer>[] adj;
		Edge[] edges;
		static final long INF = (long) 1e18;
		int ith = 0;

		static class Edge {
			int u;
			int v;

			Edge(int src, int dest) {
				u = src;
				v = dest;
			}
		}

		Graph(int V, int E) {
			this.N = V;
			adj = new List[V];
			for (int i = 0; i < V; i++) {
				adj[i] = new ArrayList<>();
			}
			edges = new Edge[E];
		}

		void addEdge(int src, int dest, boolean bidi) {
			this.adj[src].add(dest);
			if (bidi)
				this.adj[dest].add(src);
			edges[ith++] = new Edge(src, dest);
		}

		int connectedComp() {
			UnionFind uf = new UnionFind(N);
			for (Edge e : edges)
				uf.union(e.u, e.v);
			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				set.add(uf.find(i));
			}
			return set.size();
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

		int[] finishingTimes() {
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
					Stack dfs = new Stack();
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

		static class Stack {
			Node head;
			int size;

			Stack() {
				head = null;
				size = 0;
			}

			int size() {
				return size;
			}

			void push(int x) {
				if (head == null) {
					head = new Node(x);
				} else {
					Node node = new Node(x);
					node.next = head;
					head = node;
				}
				size++;
			}

			int pop() {
				assert size != 0;
				int ret = head.val;
				head = head.next;
				size--;
				return ret;
			}

			int peek() {
				assert head != null;
				return head.val;
			}

			static class Node {
				int val;
				Node next;

				Node(int v) {
					val = v;
				}
			}
		}

		static class UnionFind {
			int[] parent;

			public UnionFind(int N) {
				parent = new int[N];
				Arrays.fill(parent, -1);
			}

			int size() {
				return parent.length;
			}

			int find(int x) {
				assert (x >= 0 && x < size());
				int y = x;
				while (parent[y] >= 0)// find overall Leader
					y = parent[y];
				while (parent[x] >= 0) {// Path compression
					int nxt = parent[x];
					parent[x] = y;
					x = nxt;
				}
				return y;
			}

			boolean union(int x, int y) {
				x = find(x);
				y = find(y);
				if (parent[x] > parent[y]) {
					int t = x;
					x = y;
					y = t;
				}
				if (x != y) {
					parent[x] += parent[y];// rank
					parent[y] = x;// lazy
					return true;
				}
				return false;
			}
		}

	}
}
