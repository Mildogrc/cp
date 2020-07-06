public class SegmentTree<T> {

	public static interface Combiner<T> {
		T combine(T l, T r);
	}

	private int N;
	public T[] segtree;
	public T[] input;
	Combiner<T> combiner;

	public SegmentTree(T[] arr, Combiner<T> c) {
		/*
		 * Specify Type of Segment Tree with Comparator E.G.: Min -> (a, b) ->
		 * Math.min(a, b) Sum -> (a, b) -> a + b ...
		 */
		N = arr.length;
		segtree = (T[]) new Object[4 * N];
		input = arr;
		combiner = c;
		createTree(input, 0, 0, N - 1);
	}

	private void createTree(T[] arr, int node, int start, int end) {
		if (start == end) {
			segtree[node] = arr[start];
		} else {
			int mid = (start + end) / 2;
			createTree(arr, 2 * node + 1, start, mid);
			createTree(arr, 2 * node + 2, mid + 1, end);
			segtree[node] = combiner.combine(segtree[2 * node + 1], segtree[2 * node + 2]);
		}
	}

	private void update(int idx, int l, int r, int i, T v) {
		if (l == r)
			segtree[idx] = v;
		else {
			int m = (l + r) / 2;
			if (i <= m)
				update(2 * idx + 1, l, m, i, v);
			else
				update(2 * idx + 2, m + 1, r, i, v);
			segtree[idx] = combiner.combine(segtree[2 * idx + 1], segtree[2 * idx + 2]);
		}
	}

	public void update(int index, T newValue) {
		update(0, 0, N - 1, index, newValue);
	}

	private T get(int idx, int l, int r, int lhs, int rhs) {
		if (l >= lhs && r <= rhs)
			return segtree[idx];
		T ret = null;
		int m = (l + r) / 2;
		if (m >= lhs)
			ret = combine(ret, get(2 * idx + 1, l, m, lhs, rhs));
		if (m + 1 <= rhs)
			ret = combine(ret, get(2 * idx + 2, m + 1, r, lhs, rhs));
		return ret;
	}

	public T get(int l, int r) {
		return get(0, 0, N - 1, l, r);
	}

	public T combine(T a, T b) {
		if (a == null && b == null) {
			return null;
		}
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		return combiner.combine(a, b);
	}

}
