import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Timeline {
	static BufferedReader br;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		setIO("timeline");
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
		int C = Integer.parseInt(tokens[2]);
		long[] minTime = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Edge>[] adj = new List[N];
		for (int i = 0; i < N; i++) {
			minTime[i] = Integer.parseInt(st.nextToken());
			adj[i] = new ArrayList<>();
		}
		int[] indeg = new int[N];
		for (int c = 0; c < C; c++) {
			tokens = br.readLine().split(" ");
			int src = Integer.parseInt(tokens[0]) - 1;
			int dest = Integer.parseInt(tokens[1]) - 1;
			int weight = Integer.parseInt(tokens[2]);
			adj[src].add(new Edge(src, dest, weight));
			indeg[dest]++;
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (indeg[i] == 0)
				q.add(i);
		}
		while (q.size() > 0) {
			int u = q.poll();
			for (Edge e : adj[u]) {
				minTime[e.v] = Math.max(minTime[e.v], minTime[u] + e.w);
				if (--indeg[e.v] == 0)
					q.add(e.v);
			}
		}
		for (int i = 0; i < N; i++)
			pw.println(minTime[i]);
		pw.close();
	}

	static class Edge {
		int u;
		int v;
		int w;

		Edge(int src, int dest, int weight) {
			u = src;
			v = dest;
			w = weight;
		}
	}

	public static void setIO() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			pw = new PrintWriter(System.out);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void setIO(String s) {
		try {
			br = new BufferedReader(new FileReader(s + ".in"));
			pw = new PrintWriter(new BufferedWriter(new FileWriter(s + ".out")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
