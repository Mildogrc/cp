import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TOPOSORT {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
		List<Integer>[] adj = new List[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
		int[] indeg = new int[N];
		for (int i = 0; i < M; i++) {
			tokens = br.readLine().split(" ");
			int src = Integer.parseInt(tokens[0]) - 1;
			int dest = Integer.parseInt(tokens[1]) - 1;
			adj[src].add(dest);
			indeg[dest]++;
		}
		Queue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			if (indeg[i] == 0)
				queue.add(i);
		}
		int[] order = new int[N];
		Arrays.fill(order, -1);
		int t = 0;
		for (int i = 0; i < N; i++) {
			if (queue.isEmpty()) {
				System.out.println("Sandro fails.");
				return;
			}
			int u = queue.poll();
			order[t++] = u;
			for (int v : adj[u]) {
				if (--indeg[v] == 0) {
					queue.add(v);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (order[i] == -1) {
				System.out.println("Sandro fails.");
				return;
			}
			sb.append(order[i] + 1).append(" ");
		}
		System.out.println(sb);
	}
}
