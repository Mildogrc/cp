import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Gaurd {
	static FastInput in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO("guard");
		final int N = in.nextInt();
		final int H = in.nextInt();
		Cow[] cows = new Cow[N];
		for (int i = 0; i < N; i++)
			cows[i] = new Cow(in.nextInt(), in.nextInt(), in.nextInt());
		Arrays.sort(cows, (a, b) -> a.s == b.s ? Integer.compare(b.w, a.w) : Integer.compare(b.s, a.s));
//		System.out.println(Arrays.toString(cows));
		int max = -1;
		o: for (int i = 1; i < (1 << N); i++) {
			Cow[] stack = create(cows, i);
			int height = stack[0].h;
			int min = stack[0].s;
			for (int j = 1; j < stack.length; j++) {
				min = min(min - stack[j].w, stack[j].s);
				if (min < 0)
					continue o;
				height += stack[j].h;
			}
			if ((height >= H || height < 0) && min > 0) {
				max = Math.max(max, min);
			}
		}
		out.println(max == -1 ? "Mark is too tall" : max);
		out.close();
	}

	public static Cow[] create(Cow[] cows, int it) {
		Cow[] ret = new Cow[Integer.bitCount(it)];
		int i = 0;
		String bs = tobsS(it, cows.length);
		for (int j = 0; j < bs.length(); j++) {
			if (bs.charAt(j) == '1') {
				ret[i++] = cows[j];
			}
		}
		return ret;
	}

	static String tobsS(int x, int N) {
		StringBuilder sb = new StringBuilder(Integer.toBinaryString(x));
		sb.reverse();
		while (sb.length() < N) {
			sb.append('0');
		}
		return sb.reverse().toString();
	}

	static class Cow {
		int h, w, s;

		Cow(int height, int weight, int strength) {
			h = height;
			w = weight;
			s = strength;
		}

		Cow(Cow c) {
			h = c.h;
			w = c.w;
			s = c.s;
		}

		@Override
		public String toString() {
			return String.format("[%d %d %d]", h, w, s);
		}
	}

	static int min(int... a) {
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			min = Math.min(min, a[i]);
		}
		return min;
	}

	public static void setIO() {
		try {
			in = new FastInput();
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void setIO(String s) {
		try {
			in = new FastInput(s + ".in");
			out = new PrintWriter(new BufferedWriter(new FileWriter(s + ".out")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	static class FastInput {
		final private int BUFFER_SIZE = 1 << 10;
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
