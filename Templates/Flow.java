import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Flow {
	int N;
	List<Integer>[] adj;
	long[][] c;

	Flow(int N) {
		adj = new List[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
		c = new long[N][N];
	}

	void addEdge(int u, int v, int capacity) {
		if (c[u][v] == 0) {
			adj[u].add(v);
		}
		c[u][v] += capacity;
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

