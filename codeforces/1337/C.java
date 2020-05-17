import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class C {
	static long[] subTreeSize;
	static boolean[] subTreeVisit;
	static boolean[] ind;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Integer>[] adj = new List[N];
		Integer[] bestNode = new Integer[N];
		subTreeSize = new long[N];
		subTreeVisit = new boolean[N];
		Arrays.fill(subTreeSize, -1);
		int[] depth = new int[N];
		boolean[] visited = new boolean[N];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			bestNode[i] = i;
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken()) - 1;
			int dest = Integer.parseInt(st.nextToken()) - 1;
			adj[src].add(dest);
			adj[dest].add(src);
		}
		Queue<Integer> bfs = new LinkedList<>();
		bfs.add(0);
		visited[0] = true;
		int dep = 0;
		while (bfs.size() > 0) {
			int sz = bfs.size();
			while (sz-- > 0) {
				int node = bfs.poll();
				depth[node] = dep;
				visited[node] = true;
				for (int child : adj[node]) {
					if (!visited[child])
						bfs.add(child);
				}
			}
			dep++;
		}
		initializeSubTree(adj, subTreeSize, 0);
		Arrays.sort(bestNode, (a, b) -> {
			return Long.compare((depth[b] - subTreeSize[b]), (depth[a] - subTreeSize[a]));
		});
		long sum = 0;
		for (int i = 0; i < K; i++) {
			sum += depth[bestNode[i]];
			sum -= subTreeSize[bestNode[i]];
		}
		System.out.println(sum);
	}
 
	public static long initializeSubTree(List<Integer>[] adj, long[] subTree, int node) {
		if (subTree[node] != -1) {
			return subTree[node];
		}
		long subsz = 0;
		subTreeVisit[node] = true;
		for (int child : adj[node]) {
			if (!subTreeVisit[child]) {
				subsz++;
				subsz += initializeSubTree(adj, subTree, child);
			}
		}
		subTree[node] = subsz;
		return subsz;
	}
 
}
