class Medium1 {
	public boolean kLengthApart(int[] nums, int k) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 1) {
				int j = 0;
				for (j = 1; j + i < nums.length && j <= k; j++) {
//					System.out.println(i + j);
					if (nums[i + j] == 1) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
