import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class task1647 {

	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int Q = fi.nextInt();
		SegmentTree<Long> sg = new SegmentTree<>(N, (a, b) -> Math.min(a, b));

		for (int i = 0; i < N; i++)
			sg.update(i, fi.nextLong());

		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++)
			sb.append(sg.get(fi.nextInt() - 1, fi.nextInt() - 1)).append("\n");
		System.out.println(sb);
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
//		segtree = (T[]) Array.newInstance(arr.getClass(), N << 2);
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

	static class FastInput {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public FastInput() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public FastInput(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}

	}
}

