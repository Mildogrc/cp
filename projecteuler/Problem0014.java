public class Problem0014 {
	public static void main(String[] args) {
		int maxN = (int) 1e6;
		int max = 0;
		int maxNum = 0;
		for (int i = 1; i <= maxN; i++) {
			int curr = lenOfColl(i);
			if (lenOfColl(i) > max) {
				max = curr;
				maxNum = i;
			}
		}
		System.out.println(maxNum);
	}

	public static int lenOfColl(long a) {
		int len = 1;
		while (a != 1) {
			len++;
			a = next(a);
		}
		return len;
	}

	public static long next(long a) {
		if (a % 2 == 0) {
			return a >> 1;
		} else {
			return 3 * a + 1;
		}
	}
}
