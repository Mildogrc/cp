class Medium1 {
	public int sumFourDivisors(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			Integer x = sumDiv(nums[i]);
			if (x != -1) {
				sum += x;
			}
		}
		return sum;
	}

	public static int sumDiv(int x) {
		int sum = 0;
		int count = 0;
		int i = 0;
		for (i = 1; i * i < x; i++) {
			if (x % i == 0) {
				sum += i + (x / i);
				count += 2;
				if (count > 4) {
					return -1;
				}
			}
		}
		if (count != 4 || i * i == x) {
			return -1;
		}
		return sum;
	}
}
