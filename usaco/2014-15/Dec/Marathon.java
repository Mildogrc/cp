import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Comparator;

public class Marathon {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO("marathon");
		String[] tokens = in.readLine().split(" ");
		final int N = Integer.parseInt(tokens[0]);
		final int Q = Integer.parseInt(tokens[1]);

		Coord[] coords = new Coord[N];
		for (int i = 0; i < N; i++)
			coords[i] = new Coord(in.readLine());

		int[] dist = new int[N];
		for (int i = 1; i < N; i++)
			dist[i] = coords[i].dist(coords[i - 1]);
		SegmentTree distance = new SegmentTree(dist, (a, b) -> a + b);

		int[] save = new int[N];
		for (int i = 1; i < N - 1; i++) {
			save[i] = (distance.get(i, i + 1)) - coords[i + 1].dist(coords[i - 1]);
		}

		SegmentTree timeSave = new SegmentTree(save, (a, b) -> Math.max(a, b));
		for (int q = 0; q < Q; q++) {
			String[] querry = in.readLine().split(" ");
			if (querry[0].equals("U")) {
				int I = Integer.parseInt(querry[1]) - 1;
				int X = Integer.parseInt(querry[2]);
				int Y = Integer.parseInt(querry[3]);

				coords[I].x = X;
				coords[I].y = Y;

				if (I > 0) {
					distance.update(I, coords[I].dist(coords[I - 1]));
				}
				if (I < N - 1) {
					distance.update(I + 1, coords[I + 1].dist(coords[I]));
				}
				if (I != 0 && I != N - 1) {
					timeSave.update(I + 0, (distance.get(I + 0, I + 1)) - coords[I + 1].dist(coords[I - 1]));
				}
				if (I > 1) {
					timeSave.update(I - 1, (distance.get(I - 1, I + 0)) - coords[I + 0].dist(coords[I - 2]));
				}
				if (I < N - 2) {
					timeSave.update(I + 1, (distance.get(I + 1, I + 2)) - coords[I + 2].dist(coords[I + 0]));
				}

			} else {
				int I = Integer.parseInt(querry[1]) - 1;
				int J = Integer.parseInt(querry[2]) - 1;
				if (I == J) {
					out.println(0);
				}
				if (I + 1 == J) {
					out.println(coords[J].dist(coords[I]));
				} else {
//					System.out.println(I + " " + J);
//					System.out.println(distance.get(I + 1, J));
//					System.out.println(timeSave.get(I + 1, J));
//					System.out.println(distance.get(I + 1, J) - timeSave.get(I + 1, J));
//					System.out.println(Arrays.toString(coords));
//					System.out.println(Arrays.toString(dist) + " " + distance.get(0, N));
//					System.out.println(Arrays.toString(save));
//					System.out.println("----");

					out.println(distance.get(I + 1, J) - timeSave.get(I + 1, J));
				}
			}
		}
//		System.out.println(Arrays.toString(coords));
//		System.out.println(Arrays.toString(dist));
//		System.out.println(Arrays.toString(save));
//
//		System.out.println(distance.get(1, 4));
//		System.out.println(timeSave.get(2, 3));

		out.close();
	}

	static class Coord {
		int x, y;

		Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Coord(String s) {
			String[] tokens = s.split(" ");
			x = Integer.parseInt(tokens[0]);
			y = Integer.parseInt(tokens[1]);
		}

		int dist(Coord c) {
			return Math.abs(x - c.x) + Math.abs(y - c.y);
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", x, y);
		}
	}

	static class SegmentTree {

		private int N;
		public Integer[] segtree;
		public int[] input;
		Comparator<Integer> comparator;

		public SegmentTree(int[] input) {
			this(input, (a, b) -> Math.max(a, b));// default to Max Segment Tree
		}

		public SegmentTree(int[] arr, Comparator<Integer> c) {
			/*
			 * Specify Type of Segment Tree with Comparator E.G.: Min -> (a, b) ->
			 * Math.min(a, b) Sum -> (a, b) -> a + b ...
			 */
			N = arr.length;
			segtree = new Integer[4 * N];
			input = arr;
			comparator = c;
			createTree(input, 0, 0, N - 1);
		}

		private void createTree(int[] arr, int node, int start, int end) {
			if (start == end) {
				segtree[node] = arr[start];
			} else {
				int mid = (start + end) / 2;
				createTree(arr, 2 * node + 1, start, mid);
				createTree(arr, 2 * node + 2, mid + 1, end);
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
			update(0, 0, N - 1, index, newValue);
		}

		private int get(int idx, int l, int r, int lhs, int rhs) {
			if (l >= lhs && r <= rhs)
				return segtree[idx];
			Integer ret = null;
			int m = (l + r) / 2;
			if (m >= lhs)
				ret = compare(ret, get(2 * idx + 1, l, m, lhs, rhs));
			if (m + 1 <= rhs)
				ret = compare(ret, get(2 * idx + 2, m + 1, r, lhs, rhs));
			return ret;
		}

		public int get(int l, int r) {
			return get(0, 0, N - 1, l, r);
		}

		public Integer compare(Integer a, Integer b) {
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
