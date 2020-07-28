import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class task1631 {
	public static void main(String[] args) throws IOException {
		FastInput in = new FastInput();
		long N = in.nextLong(), max = 0, sum = 0;
		for (int i = 0; i < N; i++) {
			long x = in.nextLong();
			max = Math.max(max, x);
			sum += x;
		}
		System.out.println(max > (sum - max) ? 2 * max : sum);
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
