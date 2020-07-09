import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class CrowdedCows {
	static BufferedReader in;
	static PrintWriter out;

	static Cow[] cows;

	public static void main(String[] args) throws IOException {
		setIO("crowded");
		String[] tokens = in.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int D = Integer.parseInt(tokens[1]);
		cows = new Cow[N];
		for (int i = 0; i < N; i++)
			cows[i] = new Cow(in.readLine());

		Arrays.sort(cows, (a, b) -> Integer.compare(a.x, b.x));
		SegmentTree<Integer> sg = new SegmentTree<>(N, (a, b) -> Math.max(a, b));

		for (int i = 0; i < N; i++)
			sg.update(i, cows[i].h);

		int count = 0;
		for (int i = 0; i < N; i++) {
			int l = bsearchHigh(cows[i].x - D);
			int r = bsearchLow(cows[i].x + D);
			if (crowded(sg.get(l, i), cows[i].h, sg.get(i, r))) {
				count++;
			}
		}
		out.println(count);
		out.close();
	}

	static boolean crowded(int lmax, int mid, int rmax) {
		return lmax >= (mid << 1) && rmax >= (mid << 1);
	}

	static int bsearchLow(int x) {
		int l = 0, r = cows.length - 1;
		while (l < r) {
			int mid = (l + r + 1) >> 1;
			if (cows[mid].x <= x) {
				l = mid;
			} else {
				r = mid - 1;
			}
		}
		return l;
	}

	static int bsearchHigh(int x) {
		int l = 0, r = cows.length - 1;
		while (l < r) {
			int mid = (l + r) >> 1;
			if (cows[mid].x >= x) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}

	static class Cow {
		int x, h;

		Cow(String s) {
			String[] tokens = s.split(" ");
			x = Integer.parseInt(tokens[0]);
			h = Integer.parseInt(tokens[1]);
		}

		Cow(int x, int h) {
			this.x = x;
			this.h = h;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, h);
		}
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
				segtree[node] = combine(segtree[2 * node + 1], segtree[2 * node + 2]);
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
				segtree[idx] = combine(segtree[2 * idx + 1], segtree[2 * idx + 2]);
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

