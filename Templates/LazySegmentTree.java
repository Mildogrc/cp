public class LazySegmentTree<T, K> {
	public static interface Combiner<T, K> {
		T combine(T l, K r);
	}
	int N;
	public T[] segTree;
	public K[] lazy;
	public T[] input;
	public Combiner<T, T> c;
	public Combiner<K, K> push;
	public Combiner<T, K> fix;

	LazySegmentTree(int N, Combiner<T, T> c, Combiner<K, K> push, Combiner<T, K> fix) {
		this((T[]) new Object[N], c, push, fix);
	}

	LazySegmentTree(T[] input, Combiner<T, T> combinerup, Combiner<K, K> combinerdown, Combiner<T, K> fix) {
		N = input.length;
		segTree = (T[]) new Object[N << 2];
		lazy = (K[]) new Object[N << 2];
		this.input = input;
		c = combinerup;
		push = combinerdown;
		this.fix = fix;
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
		segTree[node] = combine(segTree[2 * node + 1], segTree[2 * node + 2]);
	}

	public void update(int node, K change) {
		update(node, node, change);
	}

	public void update(int start, int end, K change) {
		update(start, end, change, 0, input.length - 1, 0);
	}

	private void update(int start, int end, K change, int lhs, int rhs, int node) {
		if (lhs > rhs) {
			return;
		}
		if (lazy[node] != null) {
			segTree[node] = fix.combine(segTree[node], lazy[node]);
			if (lhs != rhs) {
				lazy[2 * node + 1] = push.combine(lazy[2 * node + 1], lazy[node]);
				lazy[2 * node + 2] = push.combine(lazy[2 * node + 2], lazy[node]);
			}
			lazy[node] = null;
		}

		if (start > rhs || end < lhs) {
			return;
		}

		if (start <= lhs && end >= rhs) {
			segTree[node] = fix.combine(segTree[node], change);
			if (lhs != rhs) {
				lazy[2 * node + 1] = push.combine(lazy[2 * node + 1], change);
				lazy[2 * node + 2] = push.combine(lazy[2 * node + 2], change);
			}
			lazy[node] = null;
		}

		int mid = (lhs + rhs) / 2;
		update(start, end, change, lhs, mid, 2 * node + 1);
		update(start, end, change, mid + 1, rhs, 2 * node + 2);
		segTree[node] = combine(segTree[2 * node + 1], segTree[2 * node + 2]);
	}

	public T get(int l, int r) {
		return get(l, r, 0, N - 1, 0);
	}

	private T get(int l, int r, int lhs, int rhs, int idx) {
		if (lhs > rhs) {
			return null;
		}
		if (lazy[idx] != null) {
			segTree[idx] = fix.combine(segTree[idx], lazy[idx]);
			if (lhs != rhs) {
				lazy[2 * idx + 1] = push.combine(lazy[2 * idx + 1], lazy[idx]);
				lazy[2 * idx + 2] = push.combine(lazy[2 * idx + 2], lazy[idx]);
			}
			lazy[idx] = null;
		}

		if (l > rhs || r < lhs) {
			return null;
		}

		if (l <= lhs && r >= rhs) {
			return segTree[idx];
		}

		int mid = (lhs + rhs) / 2;
		return combine(get(l, r, lhs, mid, 2 * idx + 1), get(l, r, mid + 1, rhs, 2 * idx + 2));

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
		return c.combine(a, b);
	}

}
