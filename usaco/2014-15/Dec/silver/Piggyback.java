import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Piggyback {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO("piggyback");
		String[] tokens = in.readLine().split(" ");
		int Bess = Integer.parseInt(tokens[0]);
		int Elsi = Integer.parseInt(tokens[1]);
		int Both = Integer.parseInt(tokens[2]);
		int N = Integer.parseInt(tokens[3]);
		int M = Integer.parseInt(tokens[4]);

		List<Integer>[] adj = new List[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			tokens = in.readLine().split(" ");
			int u = Integer.parseInt(tokens[0]) - 1;
			int v = Integer.parseInt(tokens[1]) - 1;
			adj[u].add(v);
			adj[v].add(u);
		}
		long[] B = BFS(0, adj);
		long[] E = BFS(1, adj);
		long[] D = BFS(N - 1, adj);
		for (int i = 0; i < N; i++) {
			B[i] *= Bess;
			E[i] *= Elsi;
			D[i] *= Both;
		}
		long min = B[N - 1] + E[N - 1];
		for (int d = 0; d < N; d++) {
			if (B[d] >= 0) {
				min = Math.min(min, B[d] + E[d] + D[d]);
			}
		}
		out.println(min);
		out.close();
	}

	public static long[] BFS(int src, List<Integer>[] adj) {
		boolean[] visited = new boolean[adj.length];
		long[] dist = new long[adj.length];
		Queue<Integer> queue = new LinkedList<>();

		Arrays.fill(dist, -1);
		dist[src] = 0;
		visited[src] = true;
		queue.add(src);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v : adj[u])
				if (!visited[v]) {
					dist[v] = dist[u] + 1;
					visited[v] = true;
					queue.add(v);
				}
		}
		return dist;
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
