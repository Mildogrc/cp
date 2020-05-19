import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CF1245C {
	static long ret;
	static final long mod = (int) 1e9 + 7;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] message = br.readLine().toCharArray();
		ret = 1;
		int count = 0;
		if (message[0] == 'u' || message[0] == 'n') {
			count++;
		}
		if (message[0] == 'm' || message[0] == 'w') {
			System.out.println(0);
			return;
		}
		for (int i = 1; i < message.length; i++) {
			if (message[i] == 'm' || message[i] == 'w') {
				System.out.println(0);
				return;
			}
			switch (message[i]) {
			case 'u':
				if (message[i - 1] == 'u') {
					count++;
				} else {
					count = flush(count) + 1;
				}
				break;
			case 'n':
				if (message[i - 1] == 'n') {
					count++;
				} else {
					count = flush(count) + 1;
				}
				break;
			default:
				count = flush(count);
			}
		}
		flush(count);
		System.out.println(ret);
	}

	public static int flush(int x) {
		if (x != 0) {
			long mult = getNthFib(x + 2);
			ret *= mult;
			ret %= mod;
		}
		return 0;
	}

	public static long getNthFib(int n) {
		if (n == 1)
			return 0;
		long[][] F = new long[][] { { 1, 1 }, { 1, 0 } };
		long[][] M = new long[][] { { 1, 1 }, { 1, 0 } };
		for (int i = 0; i < n - 3; i++) {
			multiply(F, M);
		}
		return F[0][0] % mod;
	}

	public static void multiply(long[][] F, long[][] M) {
		long x = (F[0][0] * M[0][0]) + (F[0][1] * M[1][0]);
		long y = (F[0][0] * M[0][1]) + (F[0][1] * M[1][1]);
		long z = (F[1][0] * M[0][0]) + (F[1][1] * M[1][0]);
		long w = (F[1][0] * M[0][1]) + (F[1][1] * M[1][1]);
		F[0][0] = x % mod;
		F[0][1] = y % mod;
		F[1][0] = z % mod;
		F[1][1] = w % mod;

	}

}
