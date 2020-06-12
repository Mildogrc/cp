import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task1160 {
	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int Q = fi.nextInt();

		int[] adj = new int[N];
		int[] in = new int[N];
		int[][] pos = new int[N][3];
		boolean[] visited = new boolean[N];

		for (int u = 0; u < N; u++) {
			int v = fi.nextInt() - 1;
			adj[u] = v;
			in[v]++;
		}

		List<List<Integer>> topo = new ArrayList<>();
		List<List<Integer>> cycle = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (!visited[i] && in[i] == 0) {
				int n = i;
				List<Integer> path = new ArrayList<>();
				while (in[n] == 0) {
					pos[n] = new int[] { 0, topo.size(), path.size() };
					visited[n] = true;
					path.add(n);
					in[adj[n]]--;
					n = adj[n];
				}
				path.add(n);
				topo.add(path);
			}
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				int n = i;
				List<Integer> cyc = new ArrayList<>();
				while (!visited[n]) {
					pos[n] = new int[] { 1, cycle.size(), cyc.size() };
					visited[n] = true;
					cyc.add(n);
					n = adj[n];
				}
				cycle.add(cyc);
			}
		}

		StringBuilder sb = new StringBuilder();
		o: for (int q = 0; q < Q; q++) {
			int A = fi.nextInt() - 1;
			int B = fi.nextInt() - 1;
			int dist = 0;
			while (true) {
				if (pos[A][0] == 0 && pos[B][0] == 0) {
					if (pos[A][1] == pos[B][1]) {
						if (pos[A][2] <= pos[B][2]) {
							dist += pos[B][2] - pos[A][2];
							sb.append(dist + "\n");
						} else {
							sb.append("-1\n");
						}
						continue o;
					} else {
						int size = topo.get(pos[A][1]).size();
						dist += size - pos[A][2] - 1;
						A = topo.get(pos[A][1]).get(size - 1);
					}
				} else if (pos[A][0] == 0 && pos[B][0] == 1) {
					int size = topo.get(pos[A][1]).size();
					dist += size - pos[A][2] - 1;
					A = topo.get(pos[A][1]).get(size - 1);
				} else if (pos[A][0] == 1 && pos[B][0] == 0) {
					sb.append("-1\n");
					continue o;
				} else {// 0 1 2 3 4 5 6 7 8 9 10 sz 11
					if (pos[A][1] == pos[B][1]) {
						int size = cycle.get(pos[A][1]).size();
						if (pos[A][2] <= pos[B][2]) {
							dist += pos[B][2] - pos[A][2];
							sb.append(dist + "\n");
							continue o;
						} else {
							dist += size - (pos[A][2] - pos[B][2]);
							sb.append(dist + "\n");
							continue o;
						}
					} else {
						sb.append("-1\n");
						continue o;
					}
				}
			}
		}
		System.out.println(sb);
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
