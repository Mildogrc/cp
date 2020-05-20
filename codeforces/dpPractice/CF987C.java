import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CF987C {
	static int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] s = new int[N + 1];
		int[] c = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[N + 1][3];
		for (int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], INF);
		for (int i = 1; i <= N; i++) {
			dp[i][0] = c[i];
			int min1 = INF, min2 = INF;
			for (int j = 0; j < i; j++) {
				if (s[j] < s[i]) {
					min1 = Math.min(min1, dp[j][0]);
					min2 = Math.min(min2, dp[j][1]);
				}
				dp[i][1] = c[i] + min1;
				dp[i][2] = c[i] + min2;
			}
		}
		int min = dp[0][2];
		for (int i = 1; i <= N; i++) {
			min = Math.min(min, dp[i][2]);
		}
		System.out.println(min >= INF ? -1 : min);
	}
}
