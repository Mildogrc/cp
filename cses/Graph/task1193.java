import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class task1193 {
	static int N;
	static int M;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
 
		boolean[][] visited = new boolean[N][M];
		char[][] pred = new char[N][M];
		int[] start = new int[2];
		Coord c = null;
 
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				switch (s.charAt(j)) {
				case '#':
					visited[i][j] = true;
					pred[i][j] = '#';
					break;
				case 'A':
					start = new int[] { i, j };
					pred[i][j] = 'A';
					break;
				case 'B':
					c = new Coord(i, j);
					pred[i][j] = 'B';
					break;
				case '.':
					pred[i][j] = '_';
				}
			}
		}
		BFS(visited, start[0], start[1], pred);
		StringBuilder sb = new StringBuilder();
		int x = c.x;
		int y = c.y;
		if (pred[x][y] == 'B') {
			System.out.println("NO");
			return;
		}
		while (pred[x][y] != 'A') {
			char pos = pred[x][y];
			sb.append(pos);
			if (pos == 'U')
				x++;
			else if (pos == 'R')
				y--;
			else if (pos == 'D')
				x--;
			else if (pos == 'L')
				y++;
			else
				break;
		}
		System.out.println("YES");
		System.out.println(sb.length());
		System.out.println(sb.reverse());
	}
 
	static class Coord {
		int x, y;
 
		Coord(int a, int b) {
			x = a;
			y = b;
		}
 
		@Override
		public String toString() {
			return String.format("[%d, %d]", x, y);
		}
 
	}
 
	public static void BFS(boolean[][] visited, int i, int j, char[][] pred) {
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
				pred[x - 1][y] = 'U';
			}
			if (y > 0 && !visited[x][y - 1]) {
				q.add(x);
				q.add(y - 1);
				visited[x][y - 1] = true;
				pred[x][y - 1] = 'L';
			}
			if (x < N - 1 && !visited[x + 1][y]) {
				q.add(x + 1);
				q.add(y);
				visited[x + 1][y] = true;
				pred[x + 1][y] = 'D';
			}
			if (y < M - 1 && !visited[x][y + 1]) {
				q.add(x);
				q.add(y + 1);
				visited[x][y + 1] = true;
				pred[x][y + 1] = 'R';
			}
		}
	}
}
