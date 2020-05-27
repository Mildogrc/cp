public class Problem0027 {
	public static void main(String[] args) {
		long max = 0;
		long prod = 0;
		for (int a = -999; a <= 999; a++) {
			for (int b = -999; b <= 999; b++) {
				long curr = lenOfPrimes(a, b);
				if (curr > max) {
					max = curr;
					prod = a * b;
				}
			}
		}
		System.out.println(prod);
	}

	public static long lenOfPrimes(long a, long b) {
		long n = 0;
		while (isPrime((n * n) + (a * n) + b)) {
			n++;
		}
		return n;
	}

	static boolean isPrime(long n) {
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		for (long i = 5; i * i <= n; i = i + 6)
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
		return true;
	}
}
