import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class task1076 {
	static MultiSet<Integer> maxHeap, minHeap;

	static final int OPTIMAL_K_FOR_BIT = 100;

	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int K = fi.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = fi.nextInt();
		if (K > OPTIMAL_K_FOR_BIT) {
			BitSolve(N, K, arr);
		} else {
			BSTSolve(N, K, arr);
		}
	}

	static void BSTSolve(int N, int K, int[] arr) {
		maxHeap = new MultiSet<>();
		minHeap = new MultiSet<>();
		for (int i = 0; i < K; i++)
			addNum(arr[i]);
		StringBuilder sb = new StringBuilder();
		sb.append(findMedian() + " ");
		for (int i = K; i < N; i++) {
			addNum(arr[i]);
			remove(arr[i - K]);
			sb.append(findMedian() + " ");
		}
		System.out.println(sb);
	}

	static void BitSolve(int N, int K, int[] arr) {
		int[] map = compress(arr);

		StringBuilder sb = new StringBuilder();
		OrderStatisticTree ost = new OrderStatisticTree(N);
		for (int i = 0; i < K; i++)
			ost.add(arr[i]);

		sb.append(map[ost.getKth((K + 1) >> 1) - 1] + " ");
		for (int i = K; i < N; i++) {
			ost.add(arr[i]);
			ost.remove(arr[i - K]);
			sb.append(map[ost.getKth((K + 1) >> 1) - 1] + " ");
		}
		System.out.println(sb);
	}

	static int[] compress(int[] arr) {
		Map<Integer, Queue<Integer>> map = new HashMap<>();
		int[] aux = new int[arr.length];
		System.arraycopy(arr, 0, aux, 0, arr.length);
		Arrays.sort(aux);
		for (int i = 0; i < arr.length; i++) {
			Queue<Integer> q = map.get(aux[i]);
			if (q == null)
				q = new LinkedList<>();
			q.add(i + 1);
			map.put(aux[i], q);
		}
		for (int i = 0; i < arr.length; i++)
			arr[i] = map.get(arr[i]).poll();
		return aux;
	}

	static class OrderStatisticTree {
		int[] BIT;

		public OrderStatisticTree(int sz) {
			BIT = new int[sz];
		}

		void update(int i, int add) {
			while (i > 0 && i < BIT.length) {
				BIT[i] += add;
				i = i + (i & (-i));
			}
		}

		int sum(int i) {
			int ans = 0;
			while (i > 0) {
				ans += BIT[i];
				i = i - (i & (-i));
			}

			return ans;
		}

		int getKth(int k) {
			int l = 0;
			int h = BIT.length;
			while (l < h) {
				int mid = (l + h) / 2;
				if (k <= sum(mid))
					h = mid;
				else
					l = mid + 1;
			}

			return l;
		}

		void add(int x) {
			update(x, 1);
		}

		void remove(int x) {
			update(x, -1);
		}

		int rank(int x) {
			return sum(x);
		}
	}

	static void addNum(int n) {
		if (maxHeap.size() == 0 || n >= maxHeap.first()) {
			maxHeap.add(n);
		} else {
			minHeap.add(n);
		}
		rebalance();
	}

	static void remove(int num) {
		if (num >= maxHeap.first())
			maxHeap.remove(num);
		else
			minHeap.remove(num);
		rebalance();
	}

	static void rebalance() {
		if (minHeap.size() > maxHeap.size())
			maxHeap.add(minHeap.pollLast());
		else if (maxHeap.size() > minHeap.size() + 1)
			minHeap.add(maxHeap.pollFirst());
	}

	static int findMedian() {
		if (minHeap.size() == maxHeap.size())
			return minHeap.last();
		return maxHeap.first();
	}

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
