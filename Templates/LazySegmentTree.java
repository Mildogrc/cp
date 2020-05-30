import java.util.Arrays;
import java.util.Comparator;

public class LazySegmentTree {
	int N;
	public int[] segTree;
	public int[] lazy;
	public int[] input;
	public Comparator<Integer> comparator;
	public int DEFAULT;

	LazySegmentTree(int[] input) {
		this(input, (a, b) -> Math.max(a, b), Integer.MIN_VALUE);
	}

	LazySegmentTree(int[] input, Comparator<Integer> c, int DEFAULT) {
		N = input.length;
		segTree = new int[4 * N];
		Arrays.fill(segTree, DEFAULT);
		lazy = new int[4 * N];
		this.input = input;
		comparator = c;
		this.DEFAULT = DEFAULT;
		createTree(0, N - 1, 0);
	}

	private void createTree(int lhs, int rhs, int node) {
		if (lhs == rhs) {
			segTree[node] = input[lhs];
			return;
		}
		int mid = (lhs + rhs) / 2;
		createTree(lhs, mid, 2 * node + 1);
		createTree(mid + 1, rhs, 2 * node + 2);
		segTree[node] = comparator.compare(segTree[2 * node + 1], segTree[2 * node + 2]);
	}

	public void update(int node, int change) {
		update(node, node, change);
	}

	public void update(int start, int end, int change) {
		update(start, end, change, 0, input.length - 1, 0);
	}

	private void update(int start, int end, int change, int lhs, int rhs, int node) {
		if (lhs > rhs) {
			return;
		}
		if (lazy[node] != 0) {
			segTree[node] += lazy[node];
			if (lhs != rhs) {
				lazy[2 * node + 1] += lazy[node];
				lazy[2 * node + 2] += lazy[node];
			}
			lazy[node] = 0;
		}

		if (start > rhs || end < lhs) {
			return;
		}

		if (start <= lhs && end >= rhs) {
			segTree[node] += change;
			if (lhs != rhs) {
				lazy[2 * node + 1] += change;
				lazy[2 * node + 2] += change;
			}
			lazy[node] = 0;
		}

		int mid = (lhs + rhs) / 2;
		update(start, end, change, lhs, mid, 2 * node + 1);
		update(start, end, change, mid + 1, rhs, 2 * node + 2);
		segTree[node] = comparator.compare(segTree[2 * node + 1], segTree[2 * node + 2]);
	}

	public int get(int l, int r) {
		return get(l, r, 0, N - 1, 0);
	}

	private int get(int l, int r, int lhs, int rhs, int idx) {
		if (lhs > rhs) {
			return DEFAULT;
		}
		if (lazy[idx] != 0) {
			segTree[idx] += lazy[idx];
			if (lhs != rhs) {
				lazy[2 * idx + 1] += lazy[idx];
				lazy[2 * idx + 2] += lazy[idx];
			}
			lazy[idx] = 0;
		}

		if (l > rhs || r < lhs) {
			return DEFAULT;
		}

		if (l <= lhs && r >= rhs) {
			return segTree[idx];
		}

		int mid = (lhs + rhs) / 2;
		return comparator.compare(get(l, r, lhs, mid, 2 * idx + 1), get(l, r, mid + 1, rhs, 2 * idx + 2));

	}
}
