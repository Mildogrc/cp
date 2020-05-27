import java.math.BigInteger;

public class Problem0016 {
	public static void main(String[] args) {
		int N = 1000;
		BigInteger val = BigInteger.ONE.shiftLeft(N);
		String s = val.toString();
		long sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += s.charAt(i) - '0';
		}
		System.out.println(sum);
	}
}
