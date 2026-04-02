import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class E {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			List<Integer>[] adj = new List[N];
			for (int i = 0; i < N; i++) {
				adj[i] = new ArrayList<>();
			}
			long[] arr = new long[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}
			for (int i = 0; i < M; i++) {
				String[] tokens = br.readLine().split(" ");
				int src = Integer.parseInt(tokens[0]) - 1;
				int dest = Integer.parseInt(tokens[1]) - 1;
				adj[src].add(dest);
				adj[dest].add(src);
			}
			int[] A = new int[N];
			int[] B = new int[N];
			int[] C = new int[N];
			bfsDepth(A, adj, a);
			bfsDepth(B, adj, b);
			bfsDepth(C, adj, c);
			Arrays.sort(arr);
			for (int i = 1; i < arr.length; i++) {
				arr[i] += arr[i - 1];
			}
			long min = cost(A, B, C, 0, arr);
			for (int D = 1; D < N; D++) {
				if (A[D] + B[D] + C[D] > M)
					continue;
				min = Math.min(cost(A, B, C, D, arr), min);
			}
			System.out.println(min);
		}
 
	}
 
	public static long cost(int[] A, int[] B, int[] C, int D, long[] prefixSum) {
		int overlap = B[D];
		int baseCost = A[D] + C[D];
		if (baseCost + overlap > prefixSum.length)
			return Long.MAX_VALUE;
		long sum = 0;
		try {
			sum = overlap == 0 ? prefixSum[baseCost - 1] : prefixSum[baseCost + overlap - 1] - prefixSum[overlap - 1];
		} catch (Exception e) {
		}
		if (overlap > 0)
			sum += prefixSum[overlap - 1] * 2;
		return sum;
	}
 
	public static void bfsDepth(int[] depth, List<Integer>[] adj, int src) {
		Queue<Integer> bfs = new LinkedList<>();
		boolean[] visited = new boolean[adj.length];
		int layer = 0;
 
		bfs.add(src);
		visited[src] = true;
 
		while (bfs.size() > 0) {
			int sz = bfs.size();
			while (sz-- > 0) {
				int node = bfs.poll();
				depth[node] = layer;
				for (int child : adj[node]) {
					if (!visited[child]) {
						bfs.add(child);
						visited[child] = true;
					}
				}
			}
			layer++;
		}
	}
}
