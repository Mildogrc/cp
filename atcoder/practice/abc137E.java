import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class abc137E {
	static long INF = Long.MAX_VALUE >> 1;
 
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
			int weight = Integer.parseInt(tokens[2]) - P;
			edges[i] = new Edge(src, dest, weight);
		}
		long[] dist = new long[N];
		Arrays.fill(dist, -INF);
		dist[0] = 0;
		boolean done = true;
		for (int i = 1; i < N; i++) {
			done = true;
			for (Edge e : edges) {
				if (dist[e.u] != -INF && dist[e.v] < dist[e.u] + e.w) {
					dist[e.v] = dist[e.u] + e.w;
					done = false;
				}
			}
			if (done)
				break;
		}
		long max = dist[N - 1];
		if (!done)
			for (int i = 0; i < N; i++)
				for (Edge e : edges) {
					if (dist[e.u] != -INF && dist[e.v] < dist[e.u] + e.w) {
						dist[e.v] = INF;
					}
				}
//		System.out.println(Arrays.toString(dist));
		System.out.println(Math.abs(dist[N - 1]) >= (5e10) ? -1 : Math.max(0, max));
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
