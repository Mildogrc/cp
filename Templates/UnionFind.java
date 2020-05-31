import java.util.Arrays;

public class UnionFind {
	int[] parent;

	public UnionFind(int N) {
		parent = new int[N];
		Arrays.fill(parent, -1);
	}

	int size() {
		return parent.length;
	}

	int find(int x) {
		assert (x >= 0 && x < size());
		int y = x;
		while (parent[y] >= 0)//find overall Leader
			y = parent[y];
		while (parent[x] >= 0) {// Path compression
			int nxt = parent[x];
			parent[x] = y;
			x = nxt;
		}
		return y;
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
