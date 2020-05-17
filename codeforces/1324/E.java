import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[][] dp = new int[n + 1][h];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		for (int i = 0; i < h; i++)
			if (i >= l && i <= r)
				dp[n][i] = 1;
		for (int i = n - 1; i >= 0; i--)
			for (int j = 0; j < h; j++) {
				dp[i][j] += Math.max(dp[i + 1][(j + arr[i]) % h], dp[i + 1][(j + arr[i] - 1) % h]);
				if (j >= l && j <= r && i > 0)
					dp[i][j]++;
			}

		System.out.println(dp[0][0]);
	}
}
