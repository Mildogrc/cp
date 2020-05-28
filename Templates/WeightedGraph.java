package BaseCodeForCoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class WeightedGraph {
	int V;
	List<Edge>[] adj;
	List<Edge> edges;

	static class Edge {
		int from;
		int to;
		int weight;

		Edge(int src, int dest, int w) {
			from = src;
			to = dest;
			weight = w;
		}

		Edge(Edge e) {
			from = e.from;
			to = e.to;
			weight = e.weight;
		}

		Edge reverse() {
			return new Edge(to, from, weight);
		}

		@Override
		public String toString() {
			return String.valueOf(to);
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
			adj[e.to].add(new Edge(e.to, e.from, e.weight));
			adj[e.from].add(new Edge(e.from, e.to, e.weight));
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < adj.length; i++) {
				sb.append(adj[i].toString() + "\n");
			}
			return sb.toString();
		}

		public boolean equals(Object o) {
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
						mst1.sort((a, b) -> a.to - b.to);
						mst2.sort((a, b) -> a.to - b.to);
						if (mst1.size() != mst2.size()) {
							return false;
						}
						for (int j = 0; j < mst1.size(); j++) {
							if (mst1.get(j).to != mst2.get(j).to) {
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

	WeightedGraph(int e) {
		V = e;
		adj = new List[V];
		edges = new ArrayList<>();
		for (int i = 0; i < V; i++)
			adj[i] = new ArrayList<>();
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
		edges.add(e);
	}

	MST primMST() {
		boolean[] visited = new boolean[V];
		MST mst = new MST(V);
		for (int start = 0; start < V; start++) {
			if (visited[start])
				continue;
			mst.trees++;
			Queue<Edge> queue = new PriorityQueue<>((a, b) -> a.weight - b.weight);
			queue.addAll(adj[start]);
			visited[start] = true;
			while (!queue.isEmpty()) {
				Edge curr = queue.poll();
				if (visited[curr.to])
					continue;
				mst.addEdge(curr);
				visited[curr.to] = true;
				mst.cost += curr.weight;
				for (Edge e : adj[curr.to]) {
					if (!visited[e.to])
						queue.add(e);
				}
			}
		}
		return mst;
	}

	MST kruskulMST() {
		UnionFind uf = new UnionFind(V);// separate class
		edges.sort((a, b) -> a.weight - b.weight);
		int edgeCount = 0;
		MST mst = new MST(V);
		for (Edge e : edges) {
			if (edgeCount == V - 1) {
				break;
			}
			if (uf.union(e.from, e.to)) {
				mst.addEdge(e);
				mst.cost += e.weight;
				edgeCount++;
			}
		}
		mst.trees = V - edgeCount;
		return mst;
	}

	Path dijkstra(int src) {
		Long[] dist = new Long[V];
		int[] pred = new int[V];
		boolean[] visited = new boolean[V];
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
				if (visited[child.to])
					continue;
				long dgc = dist[node] + child.weight; // dijkstra's greedy criterion
				if (dist[child.to] == null || dgc < dist[child.to]) {
					dist[child.to] = dgc;
					pred[child.to] = node;
				}
				queue.add(child.to);
			}
		}
		return new Path(src, pred, dist);
	}

	Path bellmanFord(int src) {
		Long[] dist = new Long[V];
		int[] pred = new int[V];
		dist[src] = 0L;
		pred[src] = -1;
		for (int i = 1; i < V; i++) {
			boolean done = true;
			for (Edge e : edges) {
				if (dist[e.from] != null) {
					if (dist[e.to] == null) {
						dist[e.to] = dist[e.from] + e.weight;
						pred[e.to] = e.from;
					} else {
						if (dist[e.to] > dist[e.from] + e.weight) {
							dist[e.to] = dist[e.from] + e.weight;
							pred[e.to] = e.from;
							done = false;
						}
					}
				}
			}
			if (done)
				break;
		}
		boolean[] INF = new boolean[V];
		for (Edge e : edges) {
			if (dist[e.from] != null) {
				if (dist[e.to] > dist[e.from] + e.weight) {
					INF[e.to] = true;
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

