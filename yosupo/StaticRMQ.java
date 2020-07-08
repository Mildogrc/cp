import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class StaticRMQ {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO();
		String[] tokens = in.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int Q = Integer.parseInt(tokens[1]);

		Integer[] arr = new Integer[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		SegmentTree<Integer> sg = new SegmentTree<>(arr, (a, b) -> Math.min(a, b));

		for (int i = 0; i < Q; i++) {
			tokens = in.readLine().split(" ");
			int l = Integer.parseInt(tokens[0]);
			int r = Integer.parseInt(tokens[1]) - 1;
			out.println(sg.get(l, r));
		}

		out.close();
	}

	static class SegmentTree<T> {

		public static interface Combiner<T> {
			T combine(T l, T r);
		}

		private int N;
		public T[] segtree;
		public T[] input;
		Combiner<T> combiner;

		public SegmentTree(int N, Combiner<T> c) {
			this((T[]) new Object[N], c);
		}

		public SegmentTree(T[] arr, Combiner<T> c) {
			/*
			 * Specify Type of Segment Tree with Comparator E.G.: Min -> (a, b) ->
			 * Math.min(a, b) Sum -> (a, b) -> a + b ...
			 */
			N = arr.length;
//			segtree = (T[]) Array.newInstance(arr.getClass(), N << 2);
			segtree = (T[]) new Object[N << 2];
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

	public static void setIO() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void setIO(String s) {
		try {
			in = new BufferedReader(new FileReader(s + ".in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter(s + ".out")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
