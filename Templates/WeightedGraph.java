import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class WeightedGraph {
	int N;
	List<Edge>[] adj;
	Edge[] edges;
	int ith;
	boolean negative;

	WeightedGraph(int V, int E) {
		N = V;
		adj = new List[N];
		edges = new Edge[E];
		ith = 0;
		negative = false;
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
	}

	static class Edge {
		int u;
		int v;
		int w;

		Edge(int src, int dest, int weight) {
			u = src;
			v = dest;
			w = weight;
		}

		Edge(Edge e) {
			u = e.u;
			v = e.v;
			w = e.w;
		}

		Edge reverse() {
			return new Edge(v, u, w);
		}

		@Override
		public String toString() {
			return String.valueOf(v);
		}
	}

	void addEdge(int src, int dest, int weight) {
		addEdge(src, dest, weight, true);
	}

	void addEdge(int src, int dest, int weight, boolean bidirectional) {
		Edge e = new Edge(src, dest, weight);
		adj[src].add(e);
		if (bidirectional) {
			Edge rev = e.reverse();
			adj[dest].add(rev);
		}
		edges[ith++] = new Edge(src, dest, weight);
		if (!negative && weight < 0) {
			negative = true;
		}
	}

	static class MST {
		List<Edge>[] adj;
		long cost;
		int trees;

		MST(int V) {
			adj = new List[V];
			for (int i = 0; i < adj.length; i++)
				adj[i] = new ArrayList<>();
			cost = 0;
			trees = 0;
		}

		MST(long c, List<Edge>[] adj, int tree) {
			cost = c;
			this.adj = adj;
			trees = tree;
		}

		void addEdge(Edge e) {
			adj[e.v].add(new Edge(e.v, e.u, e.w));
			adj[e.u].add(new Edge(e.u, e.v, e.w));
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < adj.length; i++) {
				sb.append(adj[i].toString() + "\n");
			}
			return sb.toString();
		}

		public boolean equals(Object o) {// only works if all edges are unique
			if (o == this) {
				return true;
			}
			if (o instanceof MST) {
				MST mst = (MST) o;
				if (this.cost != mst.cost || this.trees != mst.trees)
					return false;
				if (mst.adj.length == this.adj.length) {
					for (int i = 0; i < this.adj.length; i++) {
						List<Edge> mst1 = new ArrayList<>(mst.adj[i]);
						List<Edge> mst2 = new ArrayList<>(this.adj[i]);
						mst1.sort((a, b) -> a.v - b.v);
						mst2.sort((a, b) -> a.v - b.v);
						if (mst1.size() != mst2.size()) {
							return false;
						}
						for (int j = 0; j < mst1.size(); j++) {
							if (mst1.get(j).v != mst2.get(j).v) {
								return false;
							}
						}
					}
					return true;
				}
				return false;
			}
			return false;
		}
	}

	MST primMST() {
		boolean[] visited = new boolean[N];
		MST mst = new MST(N);
		for (int start = 0; start < N; start++) {
			if (visited[start])
				continue;
			mst.trees++;
			Queue<Edge> queue = new PriorityQueue<>((a, b) -> a.w - b.w);
			queue.addAll(adj[start]);
			visited[start] = true;
			while (!queue.isEmpty()) {
				Edge curr = queue.poll();
				if (visited[curr.v])
					continue;
				mst.addEdge(curr);
				visited[curr.v] = true;
				mst.cost += curr.w;
				for (Edge e : adj[curr.v]) {
					if (!visited[e.v])
						queue.add(e);
				}
			}
		}
		return mst;
	}

	MST kruskalMST() {
		UnionFind uf = new UnionFind(N);// separate class
		Arrays.sort(edges, (a, b) -> a.w - b.w);
		int edgeCount = 0;
		MST mst = new MST(N);
		for (Edge e : edges) {
			if (edgeCount == N - 1) {
				break;
			}
			if (uf.union(e.u, e.v)) {
				mst.addEdge(e);
				mst.cost += e.w;
				edgeCount++;
			}
		}
		mst.trees = N - edgeCount;
		return mst;
	}

	static class Path {
		int src;
		Long[] dist;
		int[] pred;
		boolean[] INF;

		Path(int src, int[] pred, Long[] dist) {
			this.src = src;
			this.pred = pred;
			this.dist = dist;
		}

		Path(int src, int[] pred, Long[] dist, boolean[] INF) {
			this.src = src;
			this.pred = pred;
			this.dist = dist;
			this.INF = INF;
		}

	}

	Path shortestPath(int src) {// checks if negative and uses correct algo
		if (negative) {
			return bellmanFord(src);
		} else {
			return dijkstra(src);
		}
	}

	Path dijkstra(int src) {
		Long[] dist = new Long[N];
		int[] pred = new int[N];
		boolean[] visited = new boolean[N];
		dist[src] = 0L;
		pred[src] = -1;
		Queue<Integer> queue = new PriorityQueue<>((a, b) -> Long.compare(dist[a], dist[b]));
		queue.add(src);
		while (!queue.isEmpty()) {
			int node = queue.poll();
			if (visited[node])
				continue;
			visited[node] = true;
			for (Edge child : adj[node]) {
				if (visited[child.v])
					continue;
				long dgc = dist[node] + child.w; // dijkstra's greedy criterion
				if (dist[child.v] == null || dgc < dist[child.v]) {
					dist[child.v] = dgc;
					pred[child.v] = node;
				}
				queue.add(child.v);
			}
		}
		return new Path(src, pred, dist);
	}

	Path bellmanFord(int src) {
		Long[] dist = new Long[N];
		int[] pred = new int[N];
		dist[src] = 0L;
		pred[src] = -1;
		boolean done = true;
		for (int i = 1; i < N; i++) {
			done = true;
			for (Edge e : edges) {
				if (dist[e.u] != null) {
					if (dist[e.v] == null) {
						dist[e.v] = dist[e.u] + e.w;
						pred[e.v] = e.u;
					} else {
						if (dist[e.v] > dist[e.u] + e.w) {
							dist[e.v] = dist[e.u] + e.w;
							pred[e.v] = e.u;
							done = false;
						}
					}
				}
			}
			if (done)
				break;
		}
		boolean[] INF = new boolean[N];
		if (!done)
			for (int i = 0; i < N; i++)
				for (Edge e : edges) {
					if (dist[e.u] != null) {
						if (dist[e.v] > dist[e.u] + e.w) {
							INF[e.v] = true;
						}
					}
				}
		return new Path(src, pred, dist, INF);
	}

	static int[] constructPath(Path p, int dest) {
		List<Integer> path = new ArrayList<>();
		int node = p.pred[dest];
		while (node != -1) {
			path.add(node);
			node = p.pred[node];
		}
		Collections.reverse(path);
		int[] arr = new int[path.size()];
		for (int i = path.size() - 1, j = 0; i >= 0; i--, j++) {
			arr[j] = path.get(i);
		}
		return arr;
	}
}
