import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TEST {
	static int N;
	static List<Integer>[] adj;
	static long[][] c;
	static int[] pred;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
		c = new long[N][N];
		adj = new List[N];
		pred = new int[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			tokens = br.readLine().split(" ");
			int src = Integer.parseInt(tokens[0]) - 1;
			int dest = Integer.parseInt(tokens[1]) - 1;
			int capacity = Integer.parseInt(tokens[2]);
			if (c[src][dest] == 0) {
				adj[src].add(dest);
				adj[dest].add(src);
			}
			c[src][dest] += capacity;
		}
		long flow = 0;
		while (true) {
			long min = DFS(0, N - 1);
			if (min == -1) {
				break;
			} else {
				flow += min;
			}
		}
		System.out.println(flow);
	}

	public static long DFS(int src, int sink) {
		Arrays.fill(visited, false);
		Arrays.fill(pred, -1);
		visited[src] = true;
		pred[src] = 0;
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
		return pred[N - 1] == -1 ? -1 : augment(getMin(sink), sink);
	}

	public static long getMin(int sink) {
		int node = sink;
		long min = Long.MAX_VALUE;
		while (pred[node] != node) {
			min = Math.min(min, c[pred[node]][node]);
			node = pred[node];
		}
		return min;
	}

	public static long augment(long min, int sink) {
		int node = sink;
		while (pred[node] != node) {
			int parent = pred[node];
			c[parent][node] -= min;
			c[node][parent] += min;
			node = parent;
		}
		return min;
	}
}
