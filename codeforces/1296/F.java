import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
 
public class F {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] adj2 = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				adj2[i][j] = -1;
			}
		}
		List<Integer>[] adj = new List[N];
		for (int i = 0; i < N; i++) {
			List<Integer> curr = new ArrayList<>();
			adj[i] = curr;
		}
		int[][] printOrder = new int[N][2];
		for (int i = 0; i < N - 1; i++) {
			printOrder[i] = to2Int(br.readLine().split(" "));
			addedge(adj, printOrder[i][0], printOrder[i][1]);
		}
		int[][] reports = new int[Integer.parseInt(br.readLine())][3];
		for (int i = 0; i < reports.length; i++) {
			reports[i] = to3Int(br.readLine().split(" "));
		}
		sortbyColumn(reports);
		List<int[]> shortestPaths = new ArrayList<>();
		for (int i = 0; i < reports.length; i++) {
			int[] shortestPath = shortestPath(adj, reports[i][0], reports[i][1]);
			shortestPaths.add(shortestPath);
			for (int j = 0; j < shortestPath.length - 1; j++) {
				int src = shortestPath[j];
				int dest = shortestPath[j + 1];
				adj2[src][dest] = reports[i][2];
				adj2[dest][src] = reports[i][2];
			}
		}
		boolean possible = true;
		for (int i = 0; i < shortestPaths.size(); i++) {
			int[] shortestPath = shortestPaths.get(i);
			int color = reports[i][2];
			boolean pathBoolean = false;
			for (int j = 0; j < shortestPath.length - 1; j++) {
				if (color == adj2[shortestPath[j]][shortestPath[j + 1]]) {
					pathBoolean = true;
					break;
				}
			}
			if (pathBoolean) {
				continue;
			} else {
				possible = false;
				break;
			}
		}
		if (!possible) {
			System.out.println("-1");
		} else {
			System.out.println(printArrays(adj2, printOrder, reports[reports.length - 1][2]));
		}
	}
 
	private static int[] to2Int(String[] tokens) {
		int[] arr = new int[tokens.length];
		arr[0] = Integer.parseInt(tokens[0]) - 1;
		arr[1] = Integer.parseInt(tokens[1]) - 1;
		return arr;
	}
 
	private static int[] to3Int(String[] tokens) {
		int[] arr = new int[tokens.length];
		arr[0] = Integer.parseInt(tokens[0]) - 1;
		arr[1] = Integer.parseInt(tokens[1]) - 1;
		arr[2] = Integer.parseInt(tokens[2]);
		return arr;
	}
 
	public static void addedge(List<Integer>[] adj, int src, int dest) {
		adj[src].add(dest);
		adj[dest].add(src);
	}
 
	public static boolean BFS(List<Integer>[] adj, int src, int dest, int v, int pred[], int dist[]) {
		Queue<Integer> queue = new ArrayDeque<>();
 
		boolean[] visited = new boolean[v];
 
		for (int i = 0; i < v; i++) {
			visited[i] = false;
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
		}
 
		visited[src] = true;
		dist[src] = 0;
 
		queue.add(src);
 
		while (queue.size() != 0) {
			int u = queue.remove();
			for (int i = 0; i < adj[u].size(); i++) {
				if (visited[adj[u].get(i)] == false) {
					visited[adj[u].get(i)] = true;
					dist[adj[u].get(i)] = dist[u] + 1;
					pred[adj[u].get(i)] = u;
					queue.add(adj[u].get(i));
 
					if (adj[u].get(i) == dest) {
						return true;
					}
				}
			}
		}
 
		return false;
	}
 
	public static int[] shortestPath(List<Integer> adj[], int s, int dest) {
		int v = adj.length;
		int[] pred = new int[v];
		int[] dist = new int[v];
 
		if (BFS(adj, s, dest, v, pred, dist) == false) {
			return null;
		}
 
		List<Integer> path = new ArrayList<>();
		int crawl = dest;
		path.add(crawl);
		while (pred[crawl] != -1) {
			path.add(pred[crawl]);
			crawl = pred[crawl];
		}
		int[] finalPath = new int[path.size()];
		for (int i = 0; i < finalPath.length; i++) {
			finalPath[i] = path.get(i);
		}
		reverse(finalPath);
		return finalPath;
	}
 
	public static void sortbyColumn(int arr[][]) {
		Arrays.sort(arr, new Comparator<int[]>() {
 
			@Override
			public int compare(final int[] entry1, final int[] entry2) {
 
				if (entry1[2] == entry2[2]) {
					return 0;
				}
				if (entry1[2] > entry2[2]) {
					return 1;
				} else {
					return -1;
				}
			}
		});
	}
 
	public static void reverse(int a[]) {
		int n = a.length;
		for (int i = 0; i < n / 2; i++) {
			int temp = a[i];
			a[i] = a[n - i - 1];
			a[n - i - 1] = temp;
		}
	}
 
	public static String printArrays(int[][] adj2, int[][] printOrder, int largestColor) {
		String solution = "";
		for (int i = 0; i < printOrder.length - 1; i++) {
			int src = printOrder[i][0];
			int dest = printOrder[i][1];
			if (adj2[src][dest] == -1) {
				adj2[src][dest] = largestColor;
			}
			solution += adj2[src][dest] + " ";
		}
		return solution;
	}
}
