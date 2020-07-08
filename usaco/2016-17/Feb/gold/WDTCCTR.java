import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class WDTCCTR {
	static BufferedReader in;
	static PrintWriter out;

	static int N;
	static long T;
	static int[][] arr;
	static final long INF = (long) 1e18;

	public static void main(String[] args) throws IOException {
		setIO("visitfj");
		String[] tokens = in.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		T = Integer.parseInt(tokens[1]);

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		Map<Coord, List<Edge>> adj = new HashMap<>();
		Coord start = new Coord(0, 0);
		Coord end = new Coord(N - 1, N - 1);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Coord c = new Coord(i, j);
				adj.put(c, generateList(c));
			}
		}

		for (int i = 0; i < 4; i++) {
			Coord c = end.go(i);
			if (onTable(c)) {
				List<Edge> edges = adj.get(c);
				if (edges == null)
					edges = new ArrayList<>();
				edges.add(new Edge(end, T));
				adj.put(c, edges);
			}
		}
		for (int i = 0; i < 16; i++) {
			String s = Integer.toString(i, 4);
			while (s.length() < 2)
				s = "0" + s;
			Coord c = end.go(s);
			if (onTable(c)) {
				List<Edge> edges = adj.get(c);
				if (edges == null)
					edges = new ArrayList<>();
				edges.add(new Edge(end, T << 1));
				adj.put(c, edges);
			}
		}

		Set<Coord> visited = new HashSet<>();
		Map<Coord, Long> dist = new HashMap<>();
		Queue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.d, b.d));
		pq.add(new Node(start, 0));
		while (pq.size() > 0) {
			Node u = pq.poll();
			if (visited.contains(u.c))
				continue;
			for (Edge e : adj.get(u.c)) {
				Long curr = dist.get(e.v);
				if (curr == null)
					curr = INF;
				if (curr > u.d + e.w) {
					dist.put(e.v, u.d + e.w);
					pq.add(new Node(e.v, u.d + e.w));
				}
			}
			visited.add(u.c);
			if (visited.contains(end))
				break;
		}
		out.println(dist.get(end));
		out.close();
	}

	static List<Edge> generateList(Coord c) {
		List<Edge> edges = new ArrayList<>();
		Set<Coord> seen = new HashSet<>();
		for (int i = 0; i < 64; i++) {
			String s = Integer.toString(i, 4);
			while (s.length() < 3) {
				s = "0" + s;
			}
			Coord n = c.go(s);
			if (seen.contains(n))
				continue;
			seen.add(n);
			if (onTable(n)) {
				edges.add(new Edge(n, T * 3 + get(arr, n)));
			}
		}
		return edges;
	}

	static int get(int[][] arr, Coord c) {
		return arr[c.x][c.y];
	}

	static boolean onTable(Coord c) {
		return 0 <= c.x && c.x < N && 0 <= c.y && c.y < N;
	}

	static class Node {
		Coord c;
		long d;

		Node(Coord c, long d) {
			this.c = c;
			this.d = d;
		}
	}

	static class Edge {
		Coord v;
		long w;

		Edge(Coord c, long w) {
			v = c;
			this.w = w;
		}

		@Override
		public String toString() {
			return String.format("{%s: %d}", v.toString(), w);
		}
	}

	static class Coord {
		int x, y;

		Coord(int a, int b) {
			x = a;
			y = b;
		}

		Coord(String s) {
			String[] tokens = s.split(" ");
			x = Integer.parseInt(tokens[0]);
			y = Integer.parseInt(tokens[1]);
		}

		Coord(Coord c) {
			x = c.x;
			y = c.y;
		}

		double dist(Coord c) {
			double dx = x - c.x;
			double dy = y - c.y;
			return Math.sqrt(dx * dx + dy + dy);
		}

		int distSq(Coord c) {
			int dx = x - c.x;
			int dy = y - c.y;
			return dx * dx + dy * dy;
		}

		int distMH(Coord c) {
			return Math.abs(x - c.x) + Math.abs(y - c.y);
		}

		Coord up() {
			return new Coord(x, y + 1);
		}

		Coord down() {
			return new Coord(x, y - 1);
		}

		Coord left() {
			return new Coord(x - 1, y);
		}

		Coord right() {
			return new Coord(x + 1, y);
		}

		Coord go(char x) {
			if (x == 'N' || x == 'U' || x == '0')
				return this.up();
			if (x == 'E' || x == 'R' || x == '1')
				return this.right();
			if (x == 'S' || x == 'D' || x == '2')
				return this.down();
			if (x == 'W' || x == 'L' || x == '3')
				return this.left();
			return new Coord(this);
		}

		Coord go(String s) {
			Coord ret = new Coord(this);
			for (int i = 0; i < s.length(); i++)
				ret = ret.go(s.charAt(i));
			return ret;
		}

		Coord go(int i) {
			return go(String.valueOf(i));
		}

		Coord go(long l) {
			return go(String.valueOf(l));
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}

		@Override
		public int hashCode() {
			final long prime = 31;
			final int m = (int) 1e9 + 9;
			long result = 1;
			result = ((prime * result + x) % m);
			result = ((prime * result + y) % m);
			return (int) result;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null)
				return false;
			if (this == o)
				return true;
			if (o instanceof Coord) {
				Coord c = (Coord) o;
				return x == c.x && y == c.y;
			}
			return false;
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
