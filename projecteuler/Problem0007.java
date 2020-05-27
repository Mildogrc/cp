public class Problem0007 {
	public static void main(String[] args){
		int count = 0;
		int i = 1;
		int max = 10001;
		while (count < max) {
			i++;
			if (isPrime(i))
				count++;
		}
		System.out.println(i);
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
