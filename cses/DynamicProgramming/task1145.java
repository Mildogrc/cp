import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.net.InetAddress;

public class task1145 {
	public static void main(String[] args) throws IOException {
		int N = in.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = in.nextInt();

		int[] lis = new int[N];
		int p = 0;
		lis[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > lis[p]) {
				lis[++p] = arr[i];
			} else {
				int l = 0, r = p;
				while (l < r) {
					int mid = l + ((r - l) >> 1);
					if (lis[mid] < arr[i])
						l = mid + 1;
					else
						r = mid;
				}
				lis[l] = arr[i];
			}
		}
		out.println(p + 1);
		out.close();
	}

	public static <F, S> Pair<F, S> mp(F f, S s) {
		return new Pair<F, S>(f, s);
	}

	public static FastInput in = new FastInput();
	public static FastOutput out = new FastOutput();

	public static void setIO(String file) {
		try {
			in = new FastInput(file + ".in");
			out = new FastOutput(file + ".txt");
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

	static void println() {
		if (LOCAL)
			System.out.println();
	}

	static boolean LOCAL;
	static final int INF = 0x3f3f3f3f;
	static final int MOD = (int) 1e9 + 7;
	static {
		LocalityCheck l = new LocalityCheck();
		LOCAL = l.setLocal();
	}

	static class LocalityCheck {
		boolean setLocal() {
			try {
				return InetAddress.getLocalHost().getHostName().equals("Milinds-MBP");
			} catch (Exception e) {
				return false;
			}
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

