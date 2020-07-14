import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
 
public class task1667 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
 
		List<Integer>[] adj = new List[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
 
		for (int i = 0; i < M; i++) {
			tokens = br.readLine().split(" ");
			int u = Integer.parseInt(tokens[0]) - 1;
			int v = Integer.parseInt(tokens[1]) - 1;
			adj[u].add(v);
			adj[v].add(u);
		}
 
		Queue<Integer> bfs = new LinkedList<>();
		boolean[] visited = new boolean[N];
		int[] pred = new int[N];
		bfs.add(0);
		visited[0] = true;
		pred[0] = -1;
 
		while (bfs.size() > 0) {
			int u = bfs.poll();
			for (int v : adj[u])
				if (!visited[v]) {
					visited[v] = true;
					pred[v] = u;
					bfs.add(v);
				}
		}
		if (!visited[N - 1]) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		List<Integer> path = new ArrayList<>();
		int n = N - 1;
		while (pred[n] != -1) {
			path.add(n);
			n = pred[n];
		}
		path.add(0);
		Collections.reverse(path);
		System.out.println(path.size());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < path.size(); i++)
			sb.append((path.get(i) + 1) + " ");
		System.out.println(sb);
	}
}
