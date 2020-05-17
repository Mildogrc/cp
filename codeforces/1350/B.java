import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			int[] dp = new int[N];
			for (int i = N - 1; i >= 0; i--) {
				dp[i] = 1;
				int max = 0;
				for (int j = ((i + 1) * 2) - 1; j < N; j += i + 1) {
					if (arr[j] > arr[i])
						max = Math.max(max, dp[j]);
				}
				dp[i] += max;
			}
			for (int i = 0; i < N; i++) {
				dp[0] = Math.max(dp[0], dp[i]);
			}
			System.out.println(dp[0]);
		}
	}
}
