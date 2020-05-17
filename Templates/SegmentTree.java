import java.util.Comparator;

public class SegmentTree {

	private int MaxN;
	public Integer[] segtree;
	Comparator<Integer> comparator;

	public SegmentTree(int[] arr) {
		this(arr, (a, b) -> Math.max(a, b));// default to Max Segment Tree
	}

	public SegmentTree(int[] arr, Comparator<Integer> c) {
		/*
		 * Specify Type of Segment Tree with Comparator E.G.: Min -> (a, b) ->
		 * Math.min(a, b) Sum -> (a, b) -> a + b ...
		 */
		segtree = new Integer[4 * MaxN];
		comparator = c;
		build(arr, 0, 0, MaxN - 1);
	}

	private void build(int[] arr, int node, int start, int end) {
		if (start == end) {
			segtree[node] = arr[start];
		} else {
			int mid = (start + end) / 2;
			build(arr, 2 * node + 1, start, mid);
			build(arr, 2 * node + 2, mid + 1, end);
			segtree[node] = comparator.compare(segtree[2 * node + 1], segtree[2 * node + 2]);
		}
	}

	private void update(int idx, int l, int r, int i, int v) {
		if (l == r)
			segtree[idx] = v;
		else {
			int m = (l + r) / 2;
			if (i <= m)
				update(2 * idx + 1, l, m, i, v);
			else
				update(2 * idx + 2, m + 1, r, i, v);
			segtree[idx] = comparator.compare(segtree[2 * idx + 1], segtree[2 * idx + 2]);
		}
	}

	public void update(int index, int newValue) {
		update(0, 0, MaxN - 1, index, newValue);
	}

	private int get(int idx, int l, int r, int lhs, int rhs) {
		if (l >= lhs && r <= rhs)
			return segtree[idx];
		Integer ret = null;
		int m = (l + r) / 2;
		if (m >= lhs)
			ret = localCompare(ret, get(2 * idx + 1, l, m, lhs, rhs));
		if (m + 1 <= rhs)
			ret = localCompare(ret, get(2 * idx + 2, m + 1, r, lhs, rhs));
		return ret;
	}

	public int get(int l, int r) {
		return get(0, 0, MaxN - 1, l, r);
	}

	public Integer localCompare(Integer a, Integer b) {
		if (a == null && b == null) {
			return null;
		}
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		return comparator.compare(a, b);
	}
}
