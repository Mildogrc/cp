import java.util.ArrayList;
import java.util.List;

public class Problem0003 {
	public static void main(String[] args){
		long a = 600851475143L;
		List<Long> list = new ArrayList<>();
		for (long i = 1; i * i <= a; i++) {
			if (a % i == 0) {
				if (isPrime(a / i)) {
					System.out.println(a / i);
					return;
				}
				if (isPrime(i)) {
					list.add(i);
				}
			}
		}
		System.out.println(list.get(list.size() - 1));
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
