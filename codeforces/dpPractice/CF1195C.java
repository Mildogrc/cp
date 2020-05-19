import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CF1195CC {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N];
		int[] b = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		if (N == 1) {
			System.out.println(Math.max(a[0], b[0]));
			return;
		}
		long[][] dp = new long[N][2];
		dp[0][0] = a[0];
		dp[0][1] = b[0];
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + a[i]);
			dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + b[i]);
		}
		System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
	}

}
