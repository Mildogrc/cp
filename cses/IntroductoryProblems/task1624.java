import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class task1624 {
	static int N = 8;
	static int K = 0;
	static boolean[][] allowed;

	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		allowed = new boolean[8][8];
		for (int i = 0; i < 8; i++) {
			char[] s = fi.readLine().toCharArray();
			for (int j = 0; j < 8; j++)
				allowed[i][j] = s[j] == '.';
		}
		solve();
		System.out.println(K);
	}

	static void check(int board[][]) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (board[i][j] == 1 && !allowed[i][j])
					return;
		K++;
	}

	static boolean works(int board[][], int row, int col) {
		int i, j;
		for (i = 0; i < col; i++)
			if (board[row][i] == 1)
				return false;
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1)
				return false;
		for (i = row, j = col; j >= 0 && i < N; i++, j--)
			if (board[i][j] == 1)
				return false;

		return true;
	}

	static boolean recSolve(int board[][], int col) {
		if (col == N) {
			check(board);
			return true;
		}
		boolean res = false;
		for (int i = 0; i < N; i++) {
			if (works(board, i, col)) {
				board[i][col] = 1;
				res = recSolve(board, col + 1) || res;
				board[i][col] = 0; // BACKTRACK
			}
		}
		return res;
	}

	static void solve() {
		int board[][] = new int[N][N];

		if (recSolve(board, 0) == false) {
			System.out.printf("Solution does not exist");
			return;
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
