import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO();
		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
			solve();
		}
		out.close();
	}

	static void solve() throws IOException {
		String[] tokens = in.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
		List<Integer>[] adj = new List[N];
		List<Edge> ud = new ArrayList<>();
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			tokens = in.readLine().split(" ");
			int u = Integer.parseInt(tokens[1]) - 1;
			int v = Integer.parseInt(tokens[2]) - 1;
			if (tokens[0].equals("0")) {
				ud.add(new Edge(u, v));
			} else {
				adj[u].add(v);
			}
		}
		int[] sort = sort(adj);
		if (sort == null) {
			out.println("NO");
			return;
		}
		int[] map = new int[N];
		for (int i = 0; i < N; i++) {
			map[sort[i]] = i;
		}
		out.println("YES");
		for (int u = 0; u < N; u++)
			for (int v : adj[u])
				out.println((u + 1) + " " + (v + 1));

		for (Edge e : ud) {
			if (map[e.u] > map[e.v]) {
				out.println((e.v + 1) + " " + (e.u + 1));
			} else {
				out.println((e.u + 1) + " " + (e.v + 1));
			}
		}
	}

	static int[] sort(List<Integer>[] adj) {
		int N = adj.length;
		int[] in = new int[N];
		for (int u = 0; u < N; u++)
			for (int v : adj[u])
				in[v]++;

		int[] order = new int[N];
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < N; i++)
			if (in[i] == 0)
				q.add(i);
		int t = 0;
		for (int i = 0; i < N; i++) {
			if (q.isEmpty())
				return null;
			int u = q.poll();
			order[t++] = u;
			for (int v : adj[u]) {
				if (--in[v] == 0)
					q.add(v);
			}
		}
		return order;
	}

	static class Edge {
		int u, v;

		Edge(int a, int b) {
			u = a;
			v = b;
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
}

