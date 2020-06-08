import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class task1695{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
		Flow f = new Flow(N, M);
		List<int[]> edges = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			tokens = br.readLine().split(" ");
			int src = Integer.parseInt(tokens[0]) - 1;
			int dest = Integer.parseInt(tokens[1]) - 1;
			f.addEdge(src, dest, 1);
			f.addEdge(dest, src, 1);
			edges.add(new int[] { src, dest });
		}
		StringBuilder sb = new StringBuilder();
		sb.append(f.edmondsKarp(0, N - 1) + "\n");
		boolean[] visited = f.checkMinCut(0);
		List<int[]> bridges = new ArrayList<>();
		for (int[] e : edges) {
			if (visited[e[0]] != visited[e[1]]) {
				sb.append(String.format("%d %d\n", e[0] + 1, e[1] + 1));
			}
		}
		System.out.println(sb);
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
}
