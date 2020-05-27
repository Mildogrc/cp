public class Problem0012 {
	public static void main(String[] args) {
		int num = 1;
		int i = 1;
		while (countFactors(num) < 500) {
			num += ++i;
		}
		System.out.println(num);
	}

	public static long countFactors(long x) {
		long count = 0;
		int i = 0;
		for (i = 1; i * i < x; i++) {
			if (x % i == 0)
				count += 2;
		}
		if (i * i == x)
			count++;
		return count;
	}
}

