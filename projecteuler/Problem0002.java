public class Problem0002 {
	public static void main(String[] args){
		int prev = 1, next = 2, limit = (int) 4e6;
		long sum = 0;
		for (int i = 0; next <= limit; i++) {
			if (next % 2 == 0) {
				sum += next;
			}
			int t = next + prev;
			prev = next;
			next = t;
		}
		System.out.println(sum);
	}
}
