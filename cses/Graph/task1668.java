import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
 
public class task1668 {
 
	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput();
		int N = fi.nextInt();
		int M = fi.nextInt();
 
		List<Integer>[] adj = new List[N];
		for (int i = 0; i < N; i++)
			adj[i] = new LinkedList<>();
 
		for (int i = 0; i < M; i++) {
			int u = fi.nextInt() - 1;
			int v = fi.nextInt() - 1;
			adj[u].add(v);
			adj[v].add(u);
		}
 
		int[] dist = new int[N];
		Arrays.fill(dist, -1);
		Queue<Integer> q = new LinkedList<>();
		boolean bip = true;
		for (int st = 0; st < N; ++st) {
			if (dist[st] == -1) {
				q.add(st);
				dist[st] = 0;
				while (!q.isEmpty()) {
					int v = q.poll();
					for (int u : adj[v]) {
						if (dist[u] == -1) {
							dist[u] = dist[v] ^ 1;
							q.add(u);
						} else {
							bip &= dist[u] != dist[v];
						}
					}
				}
			}
		}
 
		if (bip) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++)
				sb.append((dist[i] + 1) + " ");
			System.out.println(sb);
		} else {
			System.out.println("IMPOSSIBLE");
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
