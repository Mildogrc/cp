public class TEST {
	public static void main(String[] args) {
		int N = 20;
		long[][] dp = new long[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			dp[0][i] = 1;
			dp[i][0] = 1;
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		System.out.println(dp[N][N]);
		/*
		 * Alternative
		 * N = rows, M = columns
		 * (N + M)!/(M! * N!)
		 * A path can be written as a series of Downs and Rights
		 * There are N downs, and M rights.
		 * The total path is of length N + M, but we only have to choose the first N.
		 * That means we get (N + M) Choose N.
		 */
	}
}
