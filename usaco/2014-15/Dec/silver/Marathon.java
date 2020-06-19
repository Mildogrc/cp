import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Marathon {
	static BufferedReader in;
	static PrintWriter out;
	static final int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		setIO("marathon");
		String[] tokens = in.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int K = Integer.parseInt(tokens[1]);

		Coord[] c = new Coord[N];
		for (int i = 0; i < N; i++)
			c[i] = new Coord(in.readLine());

		int[][] dp = new int[K + 1][N];
		for (int i = 0; i <= K; i++)
			Arrays.fill(dp[i], INF);

		dp[0][0] = 0;
		for (int i = 1; i < N; i++)
			dp[0][i] = c[i].dist(c[i - 1]) + dp[0][i - 1];

		for (int i = 1; i <= K; i++)
			for (int j = i + 1; j < N; j++)
				for (int k = i; k >= 0; k--)
					dp[i][j] = Math.min(dp[i][j], dp[i - k][j - k - 1] + c[j].dist(c[j - k - 1]));

		out.println(dp[K][N - 1]);
		out.close();
	}

	static class Coord {
		int x, y;

		Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Coord(String s) {
			String[] tokens = s.split(" ");
			x = Integer.parseInt(tokens[0]);
			y = Integer.parseInt(tokens[1]);
		}

		int dist(Coord c) {
			return Math.abs(x - c.x) + Math.abs(y - c.y);
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
