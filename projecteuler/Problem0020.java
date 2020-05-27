import java.math.BigInteger;

public class Problem0020 {
	public static void main(String[] args) {
		int N = 100;
		String fact = factorial(N).toString();
		long sum = 0;
		for (int i = 0; i < fact.length(); i++) {
			sum += fact.charAt(i) - '0';
		}
		System.out.println(sum);
	}

	public static BigInteger factorial(int x) {
		BigInteger f = BigInteger.ONE;
		for (int i = 1; i <= x; i++) {
			f = f.multiply(BigInteger.valueOf(i));
		}
		return f;
	}
}
