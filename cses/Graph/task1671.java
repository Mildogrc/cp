import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class task1671 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
		List<Edge>[] adj = new List[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			tokens = br.readLine().split(" ");
			int u = Integer.parseInt(tokens[0]) - 1;
			int v = Integer.parseInt(tokens[1]) - 1;
			long w = Long.parseLong(tokens[2]);
			adj[u].add(new Edge(v, w));
		}
		long[] dist = new long[N];
		Arrays.fill(dist, (long) 1e18);
		dist[0] = 0;
		boolean[] visited = new boolean[N];
		Queue<Integer> pq = new PriorityQueue<>((a, b) -> Long.compare(dist[a], dist[b]));
		pq.add(0);
		while (pq.size() > 0) {
			int u = pq.poll();
			if (visited[u])
				continue;
			visited[u] = true;
			for (Edge e : adj[u]) {
				if (visited[e.v])
					continue;
				if (dist[e.v] > dist[u] + e.w) {
					dist[e.v] = dist[u] + e.w;
					pq.add(e.v);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (long i : dist)
			sb.append(i + " ");
		System.out.println(sb);
	}

	static class Edge {
		int v;
		long w;

		Edge(int dest, long weight) {
			v = dest;
			w = weight;
		}
	}
}
