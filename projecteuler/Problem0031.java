import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Problem0031 {
	public static void main(String[] args) {
		int[] coins = new int[]{1,2,5,10,20,50,100,200};
        long[][] dp = new long[201][8];
        for (int i = 0; i < 8; i++) dp[0][i] = 1;
        for (int i = 0; i <= 200; i++) dp[i][0] = 1;
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j <= 200; j++) {
                dp[j][i] += dp[j][i-1];
                if (j >= coins[i]) dp[j][i] += dp[j-coins[i]][i];
            }
        }
        System.out.println(dp[200][7]);
	}
}

