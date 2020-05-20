import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class E {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			String[] tokens = br.readLine().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int K = Integer.parseInt(tokens[1]);
			String lamps = br.readLine();
			{//casework
				int[] count = new int[2];
				for (int i = 0; i < lamps.length(); i++) {
					count[lamps.charAt(i) - '0']++;
				}
				if (count[1] == 0) {
					System.out.println(0);
					continue;
				}
				if (N == K) {
					System.out.println(count[1] - 1);
					continue;
				}
				if (count[0] == 0) {
					System.out.println(N - ((N + K - 1) / K));
					continue;
				}
			}
			int[] arr = new int[lamps.length() + 0];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = lamps.charAt(i) - '0';
			}
			int[] oneB = new int[N];
			int[] oneA = new int[N];
			for (int i = 1; i < N; i++) {
				oneB[i] = oneB[i - 1] + arr[i - 1];
			}
			for (int i = N - 2; i >= 0; i--) {
				oneA[i] = oneA[i + 1] + arr[i + 1];
			}
			int[] dp = new int[N];
			for (int i = 0; i < K; i++) {
				dp[i] = oneB[i] + 1 - arr[i];
			}
			for (int i = K; i < N; i++) {
				int p = i - K;
				dp[i] = min(dp[p] + oneB[i] - oneB[p] - arr[p], oneB[i]);
				dp[i] += 1 - arr[i];
			}
			int min = N;
			for (int i = 0; i < N; i++) {
				min = min(min, dp[i] + oneA[i]);
			}
			System.out.println(min);
		}
	}
 
	public static int min(int... a) {
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			min = Math.min(min, a[i]);
		}
		return min;
	}
}
