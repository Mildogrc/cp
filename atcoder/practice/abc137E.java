import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class abc137E {
	static long INF = (long) 1e9 + 7;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
		int P = Integer.parseInt(tokens[2]);
		Edge[] edges = new Edge[M];
		for (int i = 0; i < M; i++) {
			tokens = br.readLine().split(" ");
			int src = Integer.parseInt(tokens[0]) - 1;
			int dest = Integer.parseInt(tokens[1]) - 1;
			int weight = Integer.parseInt(tokens[2]);
			edges[i] = new Edge(src, dest, weight);
		}
		long[] dist = new long[N];
		Arrays.fill(dist, -INF);
		dist[0] = 0;
		long max = 0;
		for (int i = 1; i < N; i++) {
			long[] curr = new long[N];
			Arrays.fill(curr, -INF);
			for (Edge e : edges) {
				curr[e.v] = Math.max(curr[e.v], dist[e.u] + e.w);
			}
			if (curr[N - 1] != INF)
				max = Math.max(max, curr[N - 1] - P * i);
			for (int v = 0; v < N; v++) {
				dist[v] = Math.max(dist[v], curr[v]);
			}
		}
//		System.out.println(Arrays.toString(dist));
		for (Edge e : edges) {
			if (e.v == N - 1)
				if (dist[e.v] < dist[e.u] + e.w) {
					System.out.println(-1);
					return;
				}
		}
		System.out.println(dist[N - 1] == INF ? -1 : max);
	}

	static class Edge {
		int u;
		int v;
		int w;

		Edge(int src, int dest, int w) {
			u = src;
			v = dest;
			this.w = w;
		}

		Edge(Edge e) {
			u = e.u;
			v = e.v;
			w = e.w;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d, %d]", u, v, w);
		}
	}
}
