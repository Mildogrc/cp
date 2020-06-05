public class Problem0097 {
	public static void main(String[] args) {
		long pow = 1;
		for (long i = 0; i < 7830457; i++) {
			pow <<= 1;
			pow %= 1e10;
		}
		pow *= 28433;
		pow %= 1e10;
		System.out.println(++pow);
	}
}
