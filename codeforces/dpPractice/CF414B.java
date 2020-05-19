import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CF414B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		long start = System.currentTimeMillis();
		int N = Integer.parseInt(tokens[0]);
		int K = Integer.parseInt(tokens[1]);
		long[][] dp = new long[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			dp[i][1] = 1;
		}
		for (int i = 1; i <= K; i++) {
			dp[1][i] = 1;
		}
		List<Integer>[] fact = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			List<Integer> factors = new ArrayList<>();
			int j = 0;
			for (j = 1; j * j < i; j++) {
				if (i % j == 0) {
					factors.add(j);
					factors.add(i / j);
				}
			}
			if (j * j == i) {
				factors.add(j);
			}
			fact[i] = factors;
		}
		for (int i = 2; i <= N; i++) {
			boolean pri = isPrime(i);
			for (int j = 2; j <= K; j++) {
				if (pri) {
					dp[i][j] = j;
					continue;
				}
				Mint a = new Mint(0);
				for (int k : fact[i]) {
					a.add(dp[k][j - 1]);
				}
				dp[i][j] = a.val;
			}
		}

		Mint ret = new Mint(0);
		for (int i = 0; i <= N; i++) {
			ret.add(dp[i][K]);
		}
		long end = System.currentTimeMillis();
		System.out.println(ret.val);
		System.out.println((end - start) + "ms");
	}

	static class Mint {
		long val;
		static final long mod = (int) 1e9 + 7;

		Mint(long a) {
			val = a;
		}

		void add(long a) {
			if ((val += a) >= mod)
				val -= mod;
		}

		void sub(long a) {
			if ((val -= a) < 0)
				val += mod;
		}

		void mult(long m) {
			BigInteger a = BigInteger.valueOf(val);
			BigInteger b = BigInteger.valueOf(m);
			a = a.multiply(b);
			a.mod(BigInteger.valueOf(mod));
			val = a.longValue();
		}

		void add(Mint m) {
			if ((val += m.val) >= mod)
				val -= mod;
		}

		void sub(Mint m) {
			if ((val -= m.val) < 0)
				val += mod;
		}

		void mult(Mint m) {
			BigInteger a = BigInteger.valueOf(val);
			BigInteger b = BigInteger.valueOf(m.val);
			a = a.multiply(b);
			a.mod(BigInteger.valueOf(mod));
			val = a.longValue();
		}
	}

	static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		for (int i = 5; i * i <= n; i = i + 6)
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
		return true;
	}
}
