import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem0029 {
	public static void main(String[] args) {
		int N = 100;
		Set<BigInteger> set = new HashSet<>();
		for (int a = 2; a <= N; a++) {
			for (int b = 2; b <= N; b++) {
				set.add(BigInteger.valueOf(a).pow(b));
			}
		}
		System.out.println(set.size());
	}
}

