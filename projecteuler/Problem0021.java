import java.util.HashSet;
import java.util.Set;

public class Problem0021 {
	public static void main(String[] args) {
		Set<Integer> amicable = new HashSet<>();
		for (int a = 1; a < 10000; a++) {
			if (!amicable.contains(a)) {
				int b = d(a);
				if (b != a) {
					if (a == d(b)) {
						amicable.add(a);
						amicable.add(b);
					}
				}
			}
		}
		long sum = 0;
		for (int x : amicable) {
			sum += x;
		}
		System.out.println(sum);
	}

	public static int d(int x) {
		int sum = 1;
		int i = 2;
		for (; i * i < x; i++) {
			if (x % i == 0) {
				sum += x / i + i;
			}
		}
		if (i * i == x)
			sum += i;
		return sum;
	}
}
