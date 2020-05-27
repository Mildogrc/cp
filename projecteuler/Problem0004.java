public class Problem0004 {
	public static void main(String[] args){
		int max = 0;
		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {
				int p = i * j;
				if (p > max && isPalindrome(p)) {
					max = Math.max(max, p);
				}
			}
		}
		System.out.println(max);
	}

	public static boolean isPalindrome(Object o) {
		String s = String.valueOf(o);
		return s.equals(new StringBuilder(s).reverse().toString());
	}
}

