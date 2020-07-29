import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Triangles {
	static Map<Integer, List<Coord>> X = new HashMap<>(), Y = new HashMap<>();
	static int dummy;

	public static void main(String[] args) throws IOException {
		setIO("triangles");
		int N = in.nextInt();
		List<Coord> Coords = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			Coord c = new Coord(in.nextInt(), in.nextInt());
			record(c);
			Coords.add(c);
		}
		sum(true);
		sum(false);
		int sum = 0;
		for (Coord c : Coords)
			sum = add(sum, mult(c.xsum, c.ysum));
		out.println(sum);
		out.close();
	}

	static void sum(boolean x) {
		for (Entry<Integer, List<Coord>> e : (x ? X.entrySet() : Y.entrySet())) {
			List<Coord> list = e.getValue();
			list.sort((a, b) -> Integer.compare(x ? a.y : a.x, x ? b.y : b.x));
			int[] minus = new int[list.size()], plus = new int[list.size()];
			for (int i = 1, j = list.size() - 2; i < list.size(); i++, j--) {
				minus[i] = add(minus[i - 1],
						mult((x ? list.get(i).y : list.get(i).x) - (x ? list.get(i - 1).y : list.get(i - 1).x), i));
				plus[j] = add(plus[j + 1],
						mult((x ? list.get(j + 1).y : list.get(j + 1).x) - (x ? list.get(j).y : list.get(j).x), i));
			}
			for (int i = 0; i < list.size(); i++)
				dummy = x ? (list.get(i).ysum = add(plus[i], minus[i])) : (list.get(i).xsum = add(plus[i], minus[i]));// allows one-liner
		}
	}

	static void record(Coord c) {
		List<Coord> xs = X.get(c.x);
		List<Coord> ys = Y.get(c.y);
		if (xs == null)
			xs = new ArrayList<>();
		if (ys == null)
			ys = new ArrayList<>();
		xs.add(c);
		ys.add(c);
		X.put(c.x, xs);
		Y.put(c.y, ys);
	}

	static class Coord {
		int x, y;
		int xsum, ysum;

		Coord(int a, int b) {
			x = a;
			y = b;
			xsum = 0;
			ysum = 0;
		}

		@Override
		public String toString() {
			return String.format("(%d %d)", x, y);
		}
	}

	static int add(int a, int b) {
		int c = a + b;
		return c >= MOD ? c - MOD : c;
	}

	static int mult(long a, long b) {
		return (int) (a * b % MOD);
	}

//TEMPLATE CODE
	public static <F, S> Pair<F, S> mp(F f, S s) {
		return new Pair<F, S>(f, s);
	}

	public static FastInput in = new FastInput();
	public static FastOutput out = new FastOutput();

	public static void setIO(String file) {
		try {
			in = new FastInput(file + ".in");
			out = new FastOutput(file + ".out");
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	static class Pair<F, S> {
		F f;
		S s;

		Pair(F f, S s) {
			this.f = f;
			this.s = s;
		}

		@Override
		public int hashCode() {
			int a = f == null ? 0 : f.hashCode();
			int b = s == null ? 0 : s.hashCode();
			return (a << 5) - a + b;
		}

		@Override
		public String toString() {
			return String.format("(%s, %s)", f == null ? "null" : f.toString(), s == null ? "null" : s.toString());
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null)
				return false;
			if (o instanceof Pair<?, ?>) {
				Pair<?, ?> p = (Pair<?, ?>) o;
				return ((f == p.f) || p.f.equals(f)) && (s == p.s || p.s.equals(s));
			}
			return false;
		}
	}

	static void print(Object o) {
		if (LOCAL)
			System.out.print(o);
	}

	static void println(Object o) {
		if (LOCAL)
			System.out.println(o);
	}

	static void printf(String format, Object... args) {
		if (LOCAL)
			System.out.printf(format, args);
	}

	static boolean LOCAL;
	static long INF = (long) 1e18;
	static int MOD = (int) 1e9 + 7;
	static {
		LOCAL = new File("dummy").exists();
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

	static class FastOutput {
		StringBuilder cache;
		Writer os;
		int BufferLimit = 1 << 16;

		FastOutput() {
			cache = new StringBuilder();
			os = new OutputStreamWriter(System.out);
		}

		FastOutput(String file) throws IOException {
			cache = new StringBuilder();
			os = new FileWriter(file);
		}

		void print(Object o) {
			cache.append(o.toString());
			if (cache.length() > BufferLimit)
				flush();
		}

		void println(Object o) {
			cache.append(o.toString() + '\n');
			if (cache.length() > BufferLimit)
				flush();
		}

		void println() {
			cache.append("\n");
			if (cache.length() > BufferLimit)
				flush();
		}

		void flush() {
			try {
				os.append(cache);
				os.flush();
				cache.setLength(0);
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}

		public void close() {
			flush();
			try {
				os.close();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}
	}
}

