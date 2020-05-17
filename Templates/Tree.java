import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {
	int N;
	int log;
	List<Integer>[] adj;
	int[][] anc;
	int[] depth;

	Tree(int v) {
		N = v;
		log = log(N);
		adj = new List[v];
		anc = new int[N][log];
		depth = new int[N];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
			Arrays.fill(anc[i], -1);
		}

	}

	void addEdge(int src, int dest) {
		adj[src].add(dest);
		adj[dest].add(src);
	}

	void dfs(int u, int p) {
		anc[u][0] = p;
		for (int i = 1; i < log; i++)
			anc[u][i] = anc[anc[u][i - 1]][i - 1];
		for (int v : adj[u]) {
			if (v != p) {
				depth[v] = depth[u] + 1;
				dfs(v, u);
			}
		}
	}

	int lca(int u, int v) {
		if (depth[u] < depth[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		for (int i = log - 1; i >= 0; i--) {
			if ((depth[u] - (1 << i)) >= depth[v])
				u = anc[u][i];
		}
		if (u == v)
			return u;
		for (int i = log - 1; i >= 0; i--) {
			if (anc[u][i] != anc[v][i]) {
				u = anc[u][i];
				v = anc[v][i];
			}
		}
		return anc[u][0];
	}

	private static int log(int i) {
		return Integer.numberOfTrailingZeros(Integer.highestOneBit((i >> 1 << 1) + 1)) + 1;
	}

}

