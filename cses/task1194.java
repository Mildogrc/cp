import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class task1194 {
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		boolean[][] visited = new boolean[N][M];
		char[][] direction = new char[N][M];
		List<Coord> MonstersPlacement = new ArrayList<>();
		List<Coord> Target = new ArrayList<>();
		Coord start = null;
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				switch (row.charAt(j)) {
				case '#':
					visited[i][j] = true;
					break;
				case 'M':
					MonstersPlacement.add(new Coord(i, j));
					break;
				case 'A':
					start = new Coord(i, j);
					visited[i][j] = true;
					if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
						System.out.println("YES\n0");
						return;
					}
					break;
				case '.':
					if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
						Target.add(new Coord(i, j));
					}
					break;
				}
			}
		}
		assert start != null;
		Queue<Coord> monsters = new LinkedList<>();
		Queue<Coord> path = new LinkedList<>();
		monsters.addAll(MonstersPlacement);
		path.add(start);
		for (var x : direction) {
			Arrays.fill(x, '_');
		}
		direction[start.x][start.y] = 'S';
		Coord end = null;
		bfs: while (path.size() > 0) {
			int sz = monsters.size();
			while (sz-- > 0) {
				Coord m = monsters.poll();
				if (m.x > 0 && !visited[m.x - 1][m.y]) {
					visited[m.x - 1][m.y] = true;
					monsters.add(new Coord(m.x - 1, m.y));
				}
				if (m.x < N - 1 && !visited[m.x + 1][m.y]) {
					visited[m.x + 1][m.y] = true;
					monsters.add(new Coord(m.x + 1, m.y));
				}
				if (m.y > 0 && !visited[m.x][m.y - 1]) {
					visited[m.x][m.y - 1] = true;
					monsters.add(new Coord(m.x, m.y - 1));
				}
				if (m.y < M - 1 && !visited[m.x][m.y + 1]) {
					visited[m.x][m.y + 1] = true;
					monsters.add(new Coord(m.x, m.y + 1));
				}
			}
			sz = path.size();
			while (sz-- > 0) {
				Coord p = path.poll();
				if (p.x > 0 && !visited[p.x - 1][p.y]) {
					visited[p.x - 1][p.y] = true;
					direction[p.x - 1][p.y] = 'U';
					Coord next = new Coord(p.x - 1, p.y);
					if (edge(next)) {
						end = next;
						break bfs;
					}
					path.add(next);
				}
				if (p.x < N - 1 && !visited[p.x + 1][p.y]) {
					visited[p.x + 1][p.y] = true;
					direction[p.x + 1][p.y] = 'D';
					Coord next = new Coord(p.x + 1, p.y);
					if (edge(next)) {
						end = next;
						break bfs;
					}
					path.add(next);
				}
				if (p.y > 0 && !visited[p.x][p.y - 1]) {
					visited[p.x][p.y - 1] = true;
					direction[p.x][p.y - 1] = 'L';
					Coord next = new Coord(p.x, p.y - 1);
					if (edge(next)) {
						end = next;
						break bfs;
					}
					path.add(next);
				}
				if (p.y < M - 1 && !visited[p.x][p.y + 1]) {
					visited[p.x][p.y + 1] = true;
					direction[p.x][p.y + 1] = 'R';
					Coord next = new Coord(p.x, p.y + 1);
					if (edge(next)) {
						end = next;
						break bfs;
					}
					path.add(next);
				}
			}
		}
		if (end == null) {
			System.out.println("NO");
			return;
		}
		StringBuilder sb = new StringBuilder();
		while (!end.equals(start)) {
			char dir = direction[end.x][end.y];
			switch (dir) {
			case 'U':
				sb.append('U');
				end.x++;
				break;
			case 'D':
				sb.append('D');
				end.x--;
				break;
			case 'R':
				sb.append('R');
				end.y--;
				break;
			case 'L':
				sb.append('L');
				end.y++;
				break;
			case 'S':
				end = start;
				break;
			}
		}
		System.out.println("YES");
		System.out.println(sb.length());
		System.out.println(sb.reverse());
	}

	public static boolean edge(Coord c) {
		return c.x == 0 || c.y == 0 || c.x == N - 1 || c.y == M - 1;
	}

	static class Coord {
		int x;
		int y;

		Coord(int a, int b) {
			x = a;
			y = b;
		}

		Coord(Coord c) {
			x = c.x;
			y = c.y;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", x, y);
		}

		@Override
		public int hashCode() {
			return new int[] { x, y }.hashCode();
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o instanceof Coord) {
				Coord c = (Coord) o;
				return c.x == x && c.y == y;
			}
			return false;
		}
	}
}
