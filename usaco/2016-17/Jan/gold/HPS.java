import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class HPS {
	static BufferedReader in;
	static PrintWriter out;

	static final int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		setIO("hps");
		String[] tokens = in.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int K = Integer.parseInt(tokens[1]);
		int[] fj = new int[N];
		for (int i = 0; i < N; i++)
			fj[i] = readInt(in.readLine());

		int[][][] dp = new int[K + 1][N][3];
		for (int i = 0; i <= K; i++)
			for (int j = 0; j < N; j++)
				for (int k = 0; k < 3; k++)
					dp[i][j][k] = -INF;

		dp[0][0] = new int[] { 0, 0, 0 };
		dp[0][0][fj[0]] = 1;

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				dp[0][i][j] = dp[0][i - 1][j];
				if (j == fj[i])
					dp[0][i][j]++;
			}
		}

		for (int i = 1; i <= K; i++) {
			for (int j = i; j < N; j++) {
				for (int k = 0; k < 3; k++) {
					int max = dp[i][j - 1][k];
					for (int c = 0; c < 3; c++)
						if (c != k)
							max = Math.max(max, dp[i - 1][j - 1][c]);
					if (k == fj[j])
						max++;
					dp[i][j][k] = max;
				}
			}
		}

		int max = 0;
		for (int i = K; i >= 0; i--)
			max = Math.max(max, max(dp[i][N - 1]));

		out.println(max);
		out.close();
	}

	static int max(int... a) {
		int max = a[0];
		for (int i = 1; i < a.length; i++)
			max = Math.max(max, a[i]);
		return max;

	}

	static int readInt(String s) {
		switch (s) {
		case "H":
			return 0;
		case "P":
			return 1;
		case "S":
			return 2;
		}
		return -1;
	}

	public static void setIO() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
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
