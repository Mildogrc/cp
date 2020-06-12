import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class task1682 {
	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int M = fi.nextInt();

		List<Integer>[] adj = new List[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();

		for (int i = 0; i < M; i++)
			adj[fi.nextInt() - 1].add(fi.nextInt() - 1);

		Queue<Integer> bfs = new LinkedList<>();
		int start = finishingTimes(adj)[N - 1];
		bfs.add(start);
		boolean[] visited = new boolean[N];
		visited[start] = true;
		while (bfs.size() > 0) {
			int u = bfs.poll();
			for (int v : adj[u]) {
				if (!visited[v]) {
					visited[v] = true;
					bfs.add(v);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			if (visited[start] ^ visited[i]) {
				System.out.println("NO");
				System.out.println(++start + " " + ++i);
				return;
			}
		}
		System.out.println("YES");
	}

	private static int[] finishingTimes(List<Integer>[] adj) {
		int N = adj.length;
		List<Integer>[] grev = new List[N];
		for (int i = 0; i < N; i++) {
			grev[i] = new ArrayList<>();
		}
		for (int x = 0; x < N; x++) {
			for (int y : adj[x]) {
				grev[y].add(x);
			}
		}
		int t = 0;
		int[] finish = new int[N];
		Arrays.fill(finish, -1);
		boolean[] visited = new boolean[N];
		for (int i = N - 1; i >= 0; i--) {
			if (!visited[i]) {
				Stack<Integer> dfs = new Stack<Integer>();
				dfs.push(i);
				while (dfs.size() > 0) {
					if (visited[dfs.peek()]) {
						int node = dfs.pop();
						if (finish[node] == -1)
							finish[node] = t++;
					} else {
						visited[dfs.peek()] = true;
						for (int child : grev[dfs.peek()]) {
							if (!visited[child]) {
								dfs.push(child);
							}
						}
					}
				}
			}
		}
		int[] flippedFinish = new int[finish.length];
		for (int i = 0; i < finish.length; i++) {
			flippedFinish[finish[i]] = i;
		}
		return flippedFinish;
	}

	static class FastInput {
		final private int BUFFER_SIZE = 1 << 6;
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
