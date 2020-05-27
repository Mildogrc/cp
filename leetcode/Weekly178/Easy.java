class Easy {
    public int[] smallerNumbersThanCurrent(int[] nums) {
		int[] smallArray = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			int smaller = 0;
			for (int j = 0; j < nums.length; j++) {
				if (nums[j] < nums[i]) {
					smaller++;
				}
			}
			smallArray[i] = smaller;
		}
		return smallArray;
	}
}
