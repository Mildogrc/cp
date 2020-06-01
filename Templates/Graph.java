import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
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

}
