import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class CF893C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
		List<Integer>[] adj = new List[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] cost = new int[N];
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			String[] pair = br.readLine().split(" ");
			int src = Integer.parseInt(pair[0]) - 1;
			int dest = Integer.parseInt(pair[1]) - 1;
			adj[src].add(dest);
			adj[dest].add(src);
		}
		boolean[] visited = new boolean[N];
		long sum = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			int min = cost[i];
			Queue<Integer> bfs = new LinkedList<>();
			bfs.add(i);
			while (bfs.size() > 0) {
				int sz = bfs.size();
				while (sz-- > 0) {
					int node = bfs.poll();
					if (visited[node])
						continue;
					visited[node] = true;
					min = Math.min(min, cost[node]);
					for (int child : adj[node]) {
						bfs.add(child);
					}
				}
			}
			sum += min;
		}
		System.out.println(sum);
	}
}
