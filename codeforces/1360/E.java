import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			char[][] arr = new char[N][];
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().toCharArray();
			}
			boolean[][] dp = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				dp[i][N - 1] = (arr[i][N - 1] == '1');
				dp[N - 1][i] = (arr[N - 1][i] == '1');
			}
			for (int i = N - 2; i >= 0; i--) {
				for (int j = i; j >= 0; j--) {
					if (arr[i][j] == '1') {
						dp[i][j] = (dp[i + 1][j] || dp[i][j + 1]);
					}
					if (arr[j][i] == '1') {
						dp[j][i] = (dp[j + 1][i] || dp[j][i + 1]);
					}
				}
			}
			boolean possible = true;
			o: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == '1' && !dp[i][j]) {
						possible = false;
						break o;
					}
				}
			}
			sb.append(possible ? "YES" : "NO");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
