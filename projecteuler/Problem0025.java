import java.math.BigInteger;

public class Problem0025 {
	public static void main(String[] args) {
		int N = 1000;
		BigInteger prev = BigInteger.ONE;
		BigInteger next = BigInteger.ONE;
		long count = 2;
		while (next.toString().length() < N) {
			BigInteger t = prev.add(next);
			prev = next;
			next = t;
			count++;
		}
		System.out.println(count);
	}
}

