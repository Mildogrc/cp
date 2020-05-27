public class Problem0009 {
	public static void main(String[] args) {
		o: for (int a = 1; a <= 1000; a++) {
			for (int b = 1; b <= 1000; b++) {
				if (a + b >= 1000)
					continue o;
				int c = 1000 - a - b;
				if (a * a + b * b == c * c) {
					System.out.println(a * b * c);
					return;
				}
			}
		}
	}
}

