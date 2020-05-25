import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CF706C {
	static long INF = (long) 1e14 + 7;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		String[] s = new String[N];
		String[] r = new String[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			s[i] = br.readLine();
			r[i] = reverse(s[i]);
		}
		long[][] dp = new long[2][N];
		Arrays.fill(dp[0], INF);
		Arrays.fill(dp[1], INF);
		// 0 = original, 1 = reverse
		dp[0][0] = 0;
		dp[1][0] = arr[0];
		for (int i = 1; i < N; i++) {
			if (s[i - 1].compareTo(s[i]) < 1) {
				dp[0][i] = dp[0][i - 1];
			}
			if (s[i - 1].compareTo(r[i]) < 1) {
				if (dp[0][i - 1] + arr[i] < dp[1][i]) {
					dp[1][i] = dp[0][i - 1] + arr[i];
				}
			}
			if (r[i - 1].compareTo(s[i]) < 1) {
				if (dp[1][i - 1] < dp[0][i]) {
					dp[0][i] = dp[1][i - 1];
				}
			}
			if (r[i - 1].compareTo(r[i]) < 1) {
				if (dp[1][i - 1] + arr[i] < dp[1][i]) {
					dp[1][i] = dp[1][i - 1] + arr[i];
				}
			}
		}
		System.out.println(Math.min(dp[0][N - 1], dp[1][N - 1]) >= INF ? -1 : Math.min(dp[0][N - 1], dp[1][N - 1]));
	}

	public static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}
}
