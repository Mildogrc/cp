import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Moocast {
	static BufferedReader in;
	static PrintWriter out;

	static int N;
	static Coord[] coords;

	public static void main(String[] args) throws IOException {
		setIO("moocast");

		N = Integer.parseInt(in.readLine());
		coords = new Coord[N];

		for (int i = 0; i < N; i++)
			coords[i] = new Coord(in.readLine());

		int l = 0, r = (int) 1e9;
		while (l < r) {
			int m = (l + r + 1) >> 1;
			if (ok(m))
				r = m - 1;
			else
				l = m;
		}
		out.println(l + 1);
		out.close();
	}

	public static List<Integer>[] createGraph(int X) {
		List<Integer>[] adj = new List[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N; j++) {
				if (coords[i].dist(coords[j]) <= X) {
					adj[i].add(j);
					adj[j].add(i);
				}
			}
		}
		return adj;
	}

	public static boolean[] connected(List<Integer>[] adj) {
		boolean[] visited = new boolean[N];
		Queue<Integer> bfs = new LinkedList<>();

		visited[0] = true;
		bfs.add(0);
		while (bfs.size() > 0) {
			int u = bfs.poll();
			for (int v : adj[u])
				if (!visited[v]) {
					bfs.add(v);
					visited[v] = true;
				}
		}
		return visited;
	}

	public static boolean allTrue(boolean[] arr) {
		for (boolean b : arr)
			if (!b)
				return false;
		return true;
	}

	public static boolean ok(int X) {
		return allTrue(connected(createGraph(X)));
	}

	static class Coord {
		int x, y;

		Coord(String s) {
			String[] tokens = s.split(" ");
			this.x = Integer.parseInt(tokens[0]);
			this.y = Integer.parseInt(tokens[1]);
		}

		Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int dist(Coord c) {
			int dx = x - c.x;
			int dy = y - c.y;
			return dx * dx + dy * dy;
		}
	}

	public static void setIO() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void setIO(String s) {
		try {
			in = new BufferedReader(new FileReader(s + ".in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter(s + ".out")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
