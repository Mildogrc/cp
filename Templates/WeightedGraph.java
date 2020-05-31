import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class WeightedGraph {
	static long INF = (long) 1e18;
	int N, M;
	List<Edge>[] adj;
	Edge[] edges;
	int ith;
	boolean negative;

	WeightedGraph(int V, int E) {
		N = V;
		M = E;
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
		if (weight < 0) {
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
		long[] dist;
		int[] pred;

		Path(int src, int[] pred, long[] dist) {
			this.src = src;
			this.pred = pred;
			this.dist = dist;
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

	Path shortestPath(int src) {// checks if negative and uses correct algo
		if (negative) {
			return bellmanFord(src);
		} else {
			return dijkstra(src);
		}
	}

	Path dijkstra(int src) {
		long[] dist = new long[N];
		int[] pred = new int[N];
		boolean[] visited = new boolean[N];
		Arrays.fill(dist, INF);
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
				if (dgc < dist[child.v]) {
					dist[child.v] = dgc;
					pred[child.v] = node;
				}
				queue.add(child.v);
			}
		}
		return new Path(src, pred, dist);
	}

	Path bellmanFord(int src) {
		long[] dist = new long[N];
		int[] pred = new int[N];
		dist[src] = 0L;
		pred[src] = -1;
		boolean done = true;
		for (int i = 1; i < N; i++) {
			done = true;
			for (Edge e : edges) {
				if (dist[e.v] > dist[e.u] + e.w) {
					dist[e.v] = dist[e.u] + e.w;
					pred[e.v] = e.u;
					done = false;
				}
			}
			if (done)
				break;
		}
		if (!done)
			negativeCycleCheck(dist);
		return new Path(src, pred, dist);
	}

	public void negativeCycleCheck(long[] dist) {
		for (int i = 0; i < N; i++)
			for (Edge e : edges) {
				if (dist[e.u] != INF) {
					if (dist[e.u] == -INF || dist[e.v] > dist[e.u] + e.w) {
						dist[e.v] = -INF;
					}
				}
			}
	}

	static class AllPairs {
		long[][] paths;
		int[][] B;
		Path[] dijkstraResult;

		AllPairs(long[][] a, int[][] pred) {
			paths = a;
			B = pred;
			dijkstraResult = null;
		}

		AllPairs(Path[] paths) {
			dijkstraResult = paths;
		}

		public long getDist(int src, int dest) {
			if (dijkstraResult == null) {
				return paths[src][dest];
			} else {
				return dijkstraResult[src].dist[dest];
			}
		}

		public int[] getPath(int src, int dest) {
			if (dijkstraResult == null) {
				return getFWPath(src, dest);
			} else {
				return dijkstraResult[src].getPath(dest);
			}
		}

		private int[] getFWPath(int src, int dest) {
			Node start = new Node(src);
			Node end = new Node(dest);
			start.next = end;
			Node node = start;
			while (node.next != null) {
				int mid = B[start.val][start.next.val];
				if (mid == -1) {
					node = node.next;
				} else {
					node.next = new Node(mid);
				}
			}
			List<Integer> path = new ArrayList<>();
			Node p = start;
			while (p != null) {
				path.add(p.val);
				p = p.next;
			}
			int[] arr = new int[path.size()];
			for (int i = 0, j = path.size() - 1; j >= 0 && i < path.size(); i++, j--) {
				arr[i] = path.get(j);
			}
			return arr;
		}

		private class Node {
			int val;
			Node next;

			Node(int a) {
				val = a;
				next = null;
			}
		}
	}

	public AllPairs APSP() {
		if (negative) {
			if (N * M + N * (M + N) * log(N) < N * N * N) {
				return Johnson();
			} else {
				return floydWarshall();
			}
		} else {
			if (N * (M + N) * log(N) < N * N * N) {
				Path[] paths = new Path[N];
				for (int i = 0; i < N; i++)
					paths[i] = dijkstra(i);
				return new AllPairs(paths);
			} else {
				return floydWarshall();
			}
		}
	}

	private AllPairs floydWarshall() {
		int[][] B = new int[N][N];
		long[][] dist = new long[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(B[i], -1);
			Arrays.fill(dist[i], INF);
		}
		for (int i = 0; i < N; i++) {
			dist[i][i] = 0;
			for (Edge e : adj[i]) {
				dist[i][e.v] = e.w;
			}
		}
		for (int k = 0; k < N; k++) {
			for (int u = 0; u < N; u++) {
				for (int v = 0; v < N; v++) {
					if (dist[u][k] + dist[k][v] < dist[u][v]) {
						dist[u][v] = dist[u][k] + dist[k][v];
						B[u][v] = k;
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			if (dist[i][i] < 0) {
				Queue<Integer> bfs = new LinkedList<>();
				bfs.add(i);
				while (bfs.size() > 0) {
					int size = bfs.size();
					while (size-- > 0) {
						int node = bfs.poll();
						dist[i][node] = -INF;
						for (Edge e : adj[node]) {
							if (dist[i][e.v] != -INF) {
								bfs.add(e.v);
							}
						}
					}
				}
			}
		}
		return new AllPairs(dist, B);
	}

	private AllPairs Johnson() {
		long[] p = new long[N];
		for (int i = 0; i < N; i++) {// Bellman-Ford once
			boolean done = true;
			for (Edge e : edges) {
				if (p[e.v] > p[e.u] + e.w) {
					p[e.v] = p[e.u] + e.w;
					done = false;
				}
			}
			if (done)
				break;
		}
		Path[] paths = new Path[N];
		for (int i = 0; i < N; i++) {//Dijkstra N-times
			long[] dist = new long[N];
			long[] relativeDist = new long[N];
			int[] pred = new int[N];
			boolean[] visited = new boolean[N];
			Arrays.fill(dist, INF);
			dist[i] = 0L;
			pred[i] = -1;
			Queue<Integer> queue = new PriorityQueue<>((a, b) -> Long.compare(relativeDist[a], relativeDist[b]));
			queue.add(i);
			while (!queue.isEmpty()) {
				int node = queue.poll();
				if (visited[node])
					continue;
				visited[node] = true;
				for (Edge child : adj[node]) {
					if (visited[child.v])
						continue;
					long dgc = relativeDist[node] + child.w + p[child.u] - p[child.v]; // dijkstra's greedy criterion
					if (dgc < relativeDist[child.v]) {
						relativeDist[child.v] = dgc;
						dist[child.v] = dist[child.u] + child.w;
						pred[child.v] = node;
					}
					queue.add(child.v);
				}
			}
			paths[i] = new Path(i, pred, dist);
		}
		return new AllPairs(paths);
	}

	private static int log(int i) {
		return Integer.numberOfTrailingZeros(Integer.highestOneBit((i >> 1 << 1) + 1)) + 1;
	}
}
