import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class task1644 {
	public static void main(String[] args) throws IOException {
		FastInput in = new FastInput();
		int N = in.nextInt(), MN = in.nextInt(), MX = in.nextInt();
		long arr[] = new long[N + 1];
		for (int i = 1; i <= N; i++)
			arr[i] = arr[i - 1] + in.nextInt();
		long max = Long.MIN_VALUE;
		MultiSet<Long> set = new MultiSet<>();
		for (int i = MN; i <= N; i++) {
			if (i > MX)
				set.remove(arr[i - MX - 1]);
			set.add(arr[i - MN]);
			max = Math.max(max, arr[i] - set.first());
		}
		System.out.println(max);
	}

//TEMPLATE CODE
	static class MultiSet<T> {
		private TreeMap<T, Integer> map;

		private int size;

		public MultiSet() {
			map = new TreeMap<>();
		}

		public MultiSet(Comparator<T> c) {
			map = new TreeMap<>(c);
		}

		public void add(T t) {
			Integer count = map.get(t);
			if (count == null)
				count = 0;
			map.put(t, count + 1);
			size++;
		}

		public boolean remove(T t) {
			if (map.containsKey(t)) {
				Integer count = map.get(t);
				if (count == 1)
					map.remove(t);
				else
					map.put(t, count - 1);
				size--;
				return true;
			}
			return false;
		}

		public T first() {
			return map.firstKey();
		}

		public T last() {
			return map.lastKey();
		}

		public T pollFirst() {
			if (size() == 0)
				return null;
			T ret = first();
			remove(ret);
			return ret;
		}

		public T pollLast() {
			if (size() == 0)
				return null;
			T ret = last();
			remove(ret);
			return ret;
		}

		public int count(T t) {
			return map.get(t) == null ? 0 : map.get(t);
		}

		public T lower(T t) {
			return map.lowerKey(t);
		}

		public T higher(T t) {
			return map.higherKey(t);
		}

		public T ceiling(T t) {
			return map.ceilingKey(t);
		}

		public T floor(T t) {
			return map.floorKey(t);
		}

		public int size() {
			return size;
		}

		@Override
		public String toString() {
			List<T> list = new LinkedList<>();
			for (var x : map.entrySet()) {
				for (int i = 0; i < x.getValue(); i++)
					list.add(x.getKey());
			}
			return list.toString();
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

		public String nextString() throws IOException {
			StringBuilder buf = new StringBuilder(64);
			byte c = read();
			while (c == ' ' || c == '\n')
				c = read();
			do {
				buf.append((char) c);
			} while ((c = read()) != ' ' && c != '\n');
			return buf.toString().trim();
		}

		public String readLine() throws IOException {
			StringBuilder buf = new StringBuilder(64);
			byte c = read();
			while (c <= ' ')
				c = read();
			do {
				buf.append((char) c);
			} while ((c = read()) != '\n');
			return buf.toString().trim();
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

