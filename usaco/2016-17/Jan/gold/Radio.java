import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Radio {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO("radio");
		String[] tokens = in.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]) + 1;
		int M = Integer.parseInt(tokens[1]) + 1;

		Coord[] f = new Coord[N];
		Coord[] b = new Coord[M];
		f[0] = new Coord(in.readLine());
		b[0] = new Coord(in.readLine());
		fill(f, in.readLine());
		fill(b, in.readLine());

		int[][] dp = new int[N][M];

		for (int i = 1; i < M; i++)
			dp[0][i] = dp[0][i - 1] + dist(f[0], b[i]);

		for (int i = 1; i < N; i++)
			dp[i][0] = dp[i - 1][0] + dist(f[i], b[0]);
		
		for (int i = 1; i < N; i++)
			for (int j = 1; j < M; j++)
				dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + dist(f[i], b[j]);

		out.println(dp[N - 1][M - 1]);
		out.close();
	}

	static int dist(Coord c1, Coord c2) {
		return c1.dist(c2);
	}

	static int min(int... a) {
		int min = a[0];
		for (int i : a)
			min = Math.min(i, min);
		return min;
	}

	static void fill(Coord[] c, String seq) {
		int x = c[0].x, y = c[0].y;
		for (int i = 0; i < c.length - 1; i++) {
			switch (seq.charAt(i)) {
			case 'N':
				y++;
				break;
			case 'E':
				x++;
				break;
			case 'S':
				y--;
				break;
			case 'W':
				x--;
				break;
			}
			c[i + 1] = new Coord(x, y);
		}
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

		Coord(Coord c) {
			x = c.x;
			y = c.y;
		}

		int dist(Coord c) {
			int dx = x - c.x;
			int dy = y - c.y;
			return dx * dx + dy * dy;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", x, y);
		}

		@Override
		public int hashCode() {
			return x ^ y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null)
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
