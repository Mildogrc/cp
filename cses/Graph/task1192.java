import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class task1192 {
	static int N;
	static int M;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
 
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				visited[i][j] = s.charAt(j) == '#';
			}
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					BFS(visited, i, j);
					count++;
				}
			}
		}
		System.out.println(count);
	}
 
	public static void BFS(boolean[][] visited, int i, int j) {
		visited[i][j] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(i);
		q.add(j);
		while (q.size() > 0) {
			int x = q.poll(), y = q.poll();
			if (x > 0 && !visited[x - 1][y]) {
				q.add(x - 1);
				q.add(y);
				visited[x - 1][y] = true;
			}
			if (y > 0 && !visited[x][y - 1]) {
				q.add(x);
				q.add(y - 1);
				visited[x][y - 1] = true;
			}
			if (x < N - 1 && !visited[x + 1][y]) {
				q.add(x + 1);
				q.add(y);
				visited[x + 1][y] = true;
			}
			if (y < M - 1 && !visited[x][y + 1]) {
				q.add(x);
				q.add(y + 1);
				visited[x][y + 1] = true;
			}
		}
	}
}
