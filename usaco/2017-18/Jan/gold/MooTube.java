import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class MooTube {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO("mootube");
		String[] tokens = in.readLine().split(" ");

		int N = Integer.parseInt(tokens[0]);
		int Q = Integer.parseInt(tokens[1]);

		Edge[] edges = new Edge[N - 1];
		Querry[] qs = new Querry[Q];

		for (int i = 0; i < N - 1; i++)
			edges[i] = new Edge(in.readLine());
		for (int q = 0; q < Q; q++)
			qs[q] = new Querry(in.readLine(), q);

		Arrays.sort(edges, (a, b) -> Integer.compare(b.w, a.w));
		Arrays.sort(qs, (a, b) -> Integer.compare(b.K, a.K));

		UnionFind uf = new UnionFind(N);

		int e = 0;
		for (Querry q : qs) {
			while (e < N - 1 && edges[e].w >= q.K) {
				uf.union(edges[e].u, edges[e].v);
				e++;
			}
			q.ans = uf.getComponentSize(q.v) - 1;
		}

		Arrays.sort(qs, (a, b) -> Integer.compare(a.i, b.i));
		for (Querry q : qs)
			out.println(q.ans);
		out.close();
	}

	static class UnionFind {
		int[] parent;

		public UnionFind(int N) {
			parent = new int[N];
			Arrays.fill(parent, -1);
		}

		int size() {
			return parent.length;
		}

		int getComponentSize(int x) {
			return -parent[find(x)];
		}

		int find(int x) {
			assert (x >= 0 && x < size());
			int y = x;
			while (parent[y] >= 0)// find overall Leader
				y = parent[y];
			while (parent[x] >= 0) {// Path compression
				int nxt = parent[x];
				parent[x] = y;
				x = nxt;
			}
			return y;
		}

		boolean union(int x, int y) {
			x = find(x);
			y = find(y);
			if (parent[x] > parent[y]) {
				int t = x;
				x = y;
				y = t;
			}
			if (x != y) {
				parent[x] += parent[y];// rank
				parent[y] = x;// lazy
				return true;
			}
			return false;
		}

		boolean sameSet(int x, int y) {
			return find(x) == find(y);
		}
	}

	static class Edge {
		int u, v;
		int w;

		Edge(String s) {
			String[] tokens = s.split(" ");
			u = Integer.parseInt(tokens[0]) - 1;
			v = Integer.parseInt(tokens[1]) - 1;
			w = Integer.parseInt(tokens[2]);
		}
	}

	static class Querry {
		int v, K, i;
		int ans;

		Querry(String s, int i) {
			String[] tokens = s.split(" ");
			K = Integer.parseInt(tokens[0]);
			v = Integer.parseInt(tokens[1]) - 1;
			this.i = i;
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
