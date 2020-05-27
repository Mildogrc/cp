class Medium1 {
    public int numTimesAllBlue(int[] light) {
		int count = 0;
		int sum = 0;
		int sumCheck = 0;
		for (int i = 0; i < light.length; i++) {
			sum += light[i];
			sumCheck += i + 1;
			if (sum == sumCheck) {
				count++;
			}
		}
		return count;
	}
}
