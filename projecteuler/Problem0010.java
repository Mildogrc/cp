import java.io.IOException;

public class Problem0010 {
	public static void main(String[] args) throws IOException {
		System.out.println(sumOfPrimesLessThan((int) 2e6));
	}

	public static boolean[] sieve(int x) {
		boolean[] primes = new boolean[x + 1];
		primes[0] = primes[1] = true;
		for (int i = 2; i * i <= x; i++) {
			if (!primes[i]) {
				for (int j = i << 1; j <= x; j += i) {
					primes[j] = true;
				}
			}
		}
		return primes;
	}

	public static long sumOfPrimesLessThan(int x) {
		boolean[] primes = sieve(x);
		long sum = 0;
		for (int i = 2; i <= x; i++) {
			if (!primes[i])
				sum += i;
		}
		return sum;
	}
}
