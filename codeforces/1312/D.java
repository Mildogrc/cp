import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
 
public class D {
	static long MOD = 998244353;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		System.out.println(((((ncr(m, n - 1)) * (n - 2)) % MOD) * pow(2, n - 3)) % MOD);
	}
 
	public static long ncr(int n, int r) {
		long ans = 1;
		for (int i = 1; i <= r; i++)
			ans = ((ans * (n - r + i)) % MOD * pow(i, MOD - 2)) % MOD;
		return ans;
	}
 
	public static long pow(long n, long k) {
		long x = 1;
		long y = n;
		while (k != 0) {
			if (k % 2 == 1) {
				x = x * y;
				x %= MOD;
			}
			y = y * y;
			y %= MOD;
			k = k / 2;
		}
		return x;
	}
}
