import java.util.ArrayList;
import java.util.List;

public class Problem0030 {
	public static void main(String[] args) {
		int N = 5;
		int add = (int) pow(9, N);
		int max = add;
		int ten = 10;
		while (ten < max) {
			ten *= 10;
			max += add;
		}
		long sum = 0;
		for (int i = 2; i < max; i++) {
			if (i % 1000000 == 0)
				System.out.println(i);
			int digSum = 0;
			List<Integer> digits = digits(i);
			for (int digit : digits) {
				digSum += pow(digit, N);
			}
			if (digSum == i)
				sum += i;
		}
		System.out.println(sum);
	}

	public static List<Integer> digits(int x) {
		List<Integer> dig = new ArrayList<>();
		while (x > 0) {
			dig.add(x % 10);
			x /= 10;
		}
		return dig;
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
}
