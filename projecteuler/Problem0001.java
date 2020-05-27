public class Problem0001 {
	public static void main(String[] args){
		System.out.println(sumLess(1000));
	}

	public static long sumLess(int max) {
		long sum = 0;
		for (int i = 1; i < max; i++) {
			if (i % 3 == 0 || i % 5 == 0) {
				sum += i;
			}
		}
		return sum;
	}
}
