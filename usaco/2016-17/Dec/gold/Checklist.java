import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Checklist {
	static BufferedReader in;
	static PrintWriter out;
	static final int INF = (int) 1e9 + 7;

	public static void main(String[] args) throws IOException {
		setIO("checklist");

		String[] tokens = in.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]) - 1;
		int M = Integer.parseInt(tokens[1]) + 1;

		Coord[] H = new Coord[N];
		Coord[] G = new Coord[M];
		for (int i = 0; i < N; i++)
			H[i] = new Coord(in.readLine());
		Coord end = new Coord(in.readLine());
		G[0] = H[0];
		for (int i = 1; i < M; i++)
			G[i] = new Coord(in.readLine());

		int[][][] dp = new int[M][N][2];

		for (int i = 1; i < N; i++) {
			dp[0][i][0] = dp[0][i - 1][0] + dist(H[i - 1], H[i]);
			dp[0][i][1] = INF;
		}
		for (int i = 1; i < M; i++) {
			dp[i][0][1] = dp[i - 1][0][1] + dist(G[i], G[i - 1]);
			dp[i][0][0] = INF;
		}

		for (int i = 1; i < M; i++) {
			for (int j = 1; j < N; j++) {
				dp[i][j][0] = Math.min(dp[i][j - 1][0] + dist(H[j - 1], H[j]), dp[i][j - 1][1] + dist(G[i], H[j]));
				dp[i][j][1] = Math.min(dp[i - 1][j][0] + dist(H[j], G[i]), dp[i - 1][j][1] + dist(G[i - 1], G[i]));
			}
		}
		N--;
		M--;
		out.println(Math.min(dp[M][N][0] + dist(H[N], end), dp[M][N][1] + dist(G[M], end)));
		out.close();
	}

	static int dist(Coord c1, Coord c2) {
		return c1.dist(c2);
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
