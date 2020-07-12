import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AlienPiano {
	static BufferedReader in;
	static PrintWriter out;

	static final int INF = (int) 1e9 + 7;

	public static void main(String[] args) throws IOException {
		setIO();
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine());
			int[] arr = new int[N + 1];
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());

			System.out.printf("Case #%d: %d\n", t, ans(N, arr));
		}
		out.close();
	}

	static int ans(int N, int[] arr) {
		int[][] dp = new int[4][N];
		for (int i = 0; i < 4; i++)
			Arrays.fill(dp[i], INF);
		dp[0][0] = dp[1][0] = dp[2][0] = dp[3][0] = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 4; j++) {
				int min = INF;
				if (arr[i] > arr[i - 1]) {
					for (int k = 0; k < 4; k++) {
						if (k <= j) {
							min = Math.min(min, dp[k][i - 1] + 1);
						} else {
							min = Math.min(min, dp[k][i - 1]);
						}
					}
				} else if (arr[i] == arr[i - 1]) {
					for (int k = 0; k < 4; k++) {
						if (k != j)
							min = Math.min(min, dp[k][i - 1] + 1);
						else
							min = Math.min(min, dp[k][i - 1]);
					}
				} else {
					for (int k = 0; k < 4; k++) {
						if (k >= j)
							min = Math.min(min, dp[k][i - 1] + 1);
						else
							min = Math.min(min, dp[k][i - 1]);
					}
				}
				dp[j][i] = min;
			}
		}
		int min = INF;
		for (int i = 0; i < 4; i++)
			min = Math.min(min, dp[i][N - 1]);
		return min;
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
