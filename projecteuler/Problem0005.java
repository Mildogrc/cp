import java.util.ArrayList;
import java.util.List;

public class Problem0005 {
	public static void main(String[] args){
		int max = 20;
		List<Integer> primes = primesLessThan(max);
		long prod = 1;
		for (int i = 0; i < primes.size(); i++) {
			prod *= pow(primes.get(i), (long) (Math.log(max) / Math.log(primes.get(i))));
		}
		System.out.println(prod);
	}

	public static long pow(long b, long p) {// Binary Exponentiation
		if (p == 0)
			return 1;
		long prod = 1;
		long num = b;
		while (p > 1) {
			if (p >> 1 << 1 != p) {
				prod *= num;
			}
			p >>= 1;
			num *= num;
		}
		prod *= num;
		return prod;
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

	public static List<Integer> primesLessThan(int x) {
		boolean[] sieve = sieve(x);
		List<Integer> list = new ArrayList<>();
		for (int i = 2; i <= x; i++) {
			if (!sieve[i])
				list.add(i);
		}
		return list;
	}
}
