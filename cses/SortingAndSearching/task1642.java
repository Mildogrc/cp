import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class task1642 {
	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		long target = fi.nextInt();
		long[] arr = new long[N];
		for (int i = 0; i < N; i++)
			arr[i] = fi.nextLong();

		List<Sum> pairSum = new ArrayList<>();
		for (int i = 0; i < N; i++)
			for (int j = i + 1; j < N; j++)
				pairSum.add(new Sum(arr[i] + arr[j], i, j));

		Map<Long, List<Sum>> map = new HashMap<>();
		for (Sum s1 : pairSum) {
			List<Sum> list = map.get(target - s1.sum);
			if (list == null)
				list = new ArrayList<>();
			for (Sum s2 : list) {
				if (overlap(s1, s2)) {
					System.out.println(s1 + " " + s2);
					return;
				}
			}
			list = map.get(s1.sum);
			if (list == null)
				list = new ArrayList<>();
			list.add(s1);
			map.put(s1.sum, list);
		}
		System.out.println("IMPOSSIBLE");
	}

	public static boolean overlap(Sum s1, Sum s2) {
		if (s1.i == s2.i)
			return false;
		if (s1.i == s2.j)
			return false;
		if (s1.j == s2.i)
			return false;
		if (s1.j == s2.j)
			return false;
		return true;

	}

	static class Sum {
		int i, j;
		long sum;

		Sum(long a, int b, int c) {
			sum = a;
			i = b;
			j = c;
		}

		@Override
		public String toString() {
			return String.format("%d %d", i + 1, j + 1);
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
