import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class D {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()) - 1;
		int Y = Integer.parseInt(st.nextToken()) - 1;
		int[] values = new int[N - 1];
		for (int i = 0; i < N; i++) {
//			System.out.println(i);
			boolean[] visited = new boolean[N];
			List<Integer> bfs = new ArrayList<>();
			bfs.add(i);
			visited[i] = true;
			int depth = 0;
			while (bfs.size() > 0) {
				List<Integer> aux = new ArrayList<>();
				for (int node : bfs) {
					int one = node - 1;
					int two = node + 1;
					if (one >= 0 && !visited[one]) {
						visited[one] = true;
						aux.add(one);
					}
					if (two < N && !visited[two]) {
						visited[two] = true;
						aux.add(two);
					}
					if (node == X && !visited[Y]) {
						visited[Y] = true;
						aux.add(Y);
					}
					if (node == Y && !visited[X]) {
						visited[X] = true;
						aux.add(X);
					}
				}
//				System.out.println(depth + " " + aux.toString());
				values[depth] += aux.size();
				depth++;
				bfs = new ArrayList<>(aux);
			}
		}
		for (int i = 0; i < values.length; i++) {
			System.out.println(values[i] / 2);
		}
 
//		List<Integer>[] adj = new List[N];
//		for (int i = 0; i < N; i++) {
//			adj[i] = new ArrayList<>();
//		}
 
	}
}
