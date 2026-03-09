import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E {
    public static int MOD = 998_244_353;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-->0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            String[] line = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(line[i]);
            }
            solve(arr, n);
        }
    }

    public static void solve(int[] arr, int n) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = -1;
            }
            dp[i][i] = 1;
        }
        int ans = solver(dp, 0, n - 1);
        System.out.println("ans: " + ans);
    }

    public static int solver(int[][] dp, int l, int r) {
        if (l >= r || l < 0 || r >= dp.length) return 1;
        if (dp[l][r] != -1) return dp[l][r];
        int sum = 0;
        for (int m = l; m < r; m++) {
            int prod = prod(solver(dp, l, m), solver(dp, m + 1, r));
            sum = add(sum, prod);
        }
        dp[l][r] = sum;
        return sum;
    }

    public static int add(int a, int b) {
        a = a % MOD;
        b = b % MOD;
        return (a + b) % MOD;
    }
    public static int prod(int a, int b) {
        long aL = a;
        long bL = b;
        return (int) ((aL * bL) % MOD);
    }
}