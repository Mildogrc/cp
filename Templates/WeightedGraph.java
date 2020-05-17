import java.util.ArrayList;
import java.util.Arrays;
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

		MST(long c, List<Edge>[] adj, int tree) {
			cost = c;
			this.adj = adj;
			trees = tree;
		}
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

	}

	WeightedGraph(int e) {
		V = e;
		adj = new List[V];
		edges = new ArrayList<>();
		for (int i = 0; i < V; i++)
			adj[i] = new ArrayList<>();
	}

	void addEdge(int src, int dest, int weight) {
		Edge e = new Edge(src, dest, weight);
		Edge rev = e.reverse();
		adj[src].add(e);
		adj[dest].add(rev);
		edges.add(e);
	}

	MST primMST() {
		boolean[] visited = new boolean[V];
		List<Edge>[] mst = new List[V];
		for (int i = 0; i < V; i++) {
			mst[i] = new ArrayList<>();
		}
		long cost = 0;
		int trees = 0;
		for (int start = 0; start < V; start++) {
			if (visited[start])
				continue;
			trees++;
			Queue<Edge> queue = new PriorityQueue<>((a, b) -> a.weight - b.weight);
			queue.addAll(adj[start]);
			visited[start] = true;
			while (!queue.isEmpty()) {
				Edge curr = queue.poll();
				if (visited[curr.to])
					continue;
				Edge to = new Edge(curr.from, curr.to, curr.weight);
				Edge from = new Edge(curr.to, curr.from, curr.weight);
				mst[curr.from].add(to);
				mst[curr.to].add(from);
				visited[curr.to] = true;
				cost += curr.weight;
				for (Edge e : adj[curr.to]) {
					if (!visited[e.to])
						queue.add(e);
				}
			}
		}
		return new MST(cost, mst, trees);
	}

	MST kruskulMST() {
		UnionFind uf = new UnionFind(V);// separate class
		edges.sort((a, b) -> a.weight - b.weight);
		List<Edge>[] mst = new List[V];
		for (int i = 0; i < V; i++) {
			mst[i] = new ArrayList<>();
		}
		int edgeCount = 0;
		long cost = 0;
		for (Edge e : edges) {
			if (edgeCount == V - 1) {
				break;
			}
			if (uf.union(e.from, e.to)) {
				mst[e.from].add(e);
				mst[e.to].add(e.reverse());
				cost += e.weight;
				edgeCount++;
			}
		}
		return new MST(cost, mst, V - edgeCount);
	}

	Path dijkstra(int src) {
		long[] dist = new long[V];
		int[] pred = new int[V];
		boolean[] visited = new boolean[V];
		Arrays.fill(dist, -1);
		dist[src] = 0;
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
				if (dist[child.to] == -1 || dgc < dist[child.to]) {
					dist[child.to] = dgc;
					pred[child.to] = node;
				}
				queue.add(child.to);
			}
		}
		return new Path(src, pred, dist);
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
