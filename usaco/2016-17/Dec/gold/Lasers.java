import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TEST {
	static BufferedReader in;
	static PrintWriter out;

	static Map<Integer, LinkedList<Coord>> X;
	static Map<Integer, LinkedList<Coord>> Y;

	public static void main(String[] args) throws IOException {
		setIO("lasers");

		String[] tokens = in.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		Coord start = new Coord(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
		Coord end = new Coord(Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));

		X = new HashMap<>();
		Y = new HashMap<>();
		Map<Coord, Integer> dist = new HashMap<>();

		record(end);

		for (int i = 0; i < N; i++) {
			Coord c = new Coord(in.readLine());
			record(c);
		}

		Queue<Coord> bfs = new LinkedList<>();
		bfs.add(start);
		dist.put(start, -1);

		while (bfs.size() > 0) {
			Coord u = bfs.poll();
			if (X.get(u.x) != null)
				for (Coord v : X.get(u.x)) {
					if (dist.get(v) == null) {
						dist.put(v, dist.get(u) + 1);
						bfs.add(v);
					}
				}
			if (Y.get(u.y) != null)
				for (Coord v : Y.get(u.y)) {
					if (dist.get(v) == null) {
						dist.put(v, dist.get(u) + 1);
						bfs.add(v);
					}
				}
			X.remove(u.x);
			Y.remove(u.y);
		}
		out.println(dist.get(end) == null ? -1 : dist.get(end));
		out.close();
	}

	static void record(Coord c) {
		LinkedList<Coord> adjX = X.get(c.x);
		LinkedList<Coord> adjY = Y.get(c.y);

		if (adjX == null)
			adjX = new LinkedList<>();
		if (adjY == null)
			adjY = new LinkedList<>();

		adjX.add(c);
		adjY.add(c);

		X.put(c.x, adjX);
		Y.put(c.y, adjY);
	}

	static class Coord {
		int x, y;

		Coord(String s) {
			String[] tokens = s.split(" ");
			x = Integer.parseInt(tokens[0]);
			y = Integer.parseInt(tokens[1]);
		}

		Coord(int a, int b) {
			x = a;
			y = b;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}

		@Override
		public int hashCode() {
			return x ^ y;
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
