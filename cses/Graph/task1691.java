import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class task1691 {
	public static void main(String[] args) throws IOException {
		FastInput in = new FastInput();
		int N = in.nextInt(), M = in.nextInt();
		Queue<Edge>[] adj = new Queue[N];
		for (int i = 0; i < N; i++)
			adj[i] = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			int u = in.nextInt() - 1, v = in.nextInt() - 1;
			Edge e = new Edge(u, v);
			adj[u].add(e);
			adj[v].add(e);
		}
		for (int i = 0; i < N; i++)
			if ((adj[i].size() & 1) == 1) {
				System.out.println("IMPOSSIBLE");
				return;
			}

		StringBuilder sb = new StringBuilder();
		int sz = 0;
		Stack<Integer> st = new Stack<>();
		st.add(0);
		while (st.size() > 0) {
			int u = st.peek();
			while (!adj[u].isEmpty() && adj[u].peek().u == -1)
				adj[u].poll();
			if (adj[u].size() == 0) {
				sb.append(st.pop() + 1).append(" ");
				sz++;
			} else {
				Edge e = adj[u].poll();
				int v = e.u == u ? e.v : e.u;
				e.u = -1;
				st.push(v);
			}
		}
		if (sz - 1 != M) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		System.out.println(sb);
	}

	static class Edge {
		int u, v;

		Edge(int a, int b) {
			u = a;
			v = b;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", u, v);
		}
	}

//TEMPLATE
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

