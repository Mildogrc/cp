import java.util.Arrays;

public class UnionFind {
	int[] parent;

	public UnionFind(int N) {
		parent = new int[N];
		Arrays.fill(parent, -1);
	}

	int find(int node) {
		if (parent[node] < 0) {
			return node;
		}
		return parent[node] = find(parent[node]);// path compression
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
