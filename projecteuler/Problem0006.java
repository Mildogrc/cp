public class Problem0006 {
	public static void main(String[] args) {
		long max = 100;
		long sumSq = (max * (max + 1)) >> 1;
		long squareSm = 0;
		for (int i = 1; i <= max; i++) {
			squareSm += i * i;
		}
		System.out.println(Math.abs((sumSq * sumSq) - squareSm));
	}
}

