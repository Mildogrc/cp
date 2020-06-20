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
	static List<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		setIO("moocast");

		int N = Integer.parseInt(in.readLine());
		adj = new List[N];
		Cow[] cows = new Cow[N];

		for (int i = 0; i < N; i++) {
			cows[i] = new Cow(in.readLine());
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (i != j && cows[i].inRange(cows[j]))
					adj[i].add(j);

		int max = 0;
		for (int i = 0; i < N; i++)
			max = Math.max(max, count(i));
		out.println(max);
		out.close();
	}

	public static boolean[] BFS(int src) {
		boolean[] visited = new boolean[adj.length];
		Queue<Integer> bfs = new LinkedList<>();
		bfs.add(src);
		visited[src] = true;
		while (bfs.size() > 0) {
			int u = bfs.poll();
			for (int v : adj[u]) {
				if (!visited[v]) {
					visited[v] = true;
					bfs.add(v);
				}
			}
		}
		return visited;
	}

	public static int count(int src) {
		boolean[] arr = BFS(src);
		int count = 0;
		for (boolean b : arr)
			if (b)
				count++;
		return count;
	}

	static class Cow {
		int x, y, p;

		Cow(String s) {
			String[] tokens = s.split(" ");
			x = Integer.parseInt(tokens[0]);
			y = Integer.parseInt(tokens[1]);
			p = Integer.parseInt(tokens[2]);
		}

		boolean inRange(Cow c) {
			int dx = x - c.x;
			int dy = y - c.y;
			return p * p >= dx * dx + dy * dy;
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
