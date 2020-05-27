class Easy {
    public List<Integer> luckyNumbers(int[][] matrix) {
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (isLuckyNumber(matrix, i, j)) {
					arr.add(matrix[i][j]);
				}
			}
		}
		return arr;
	}

	public static boolean isLuckyNumber(int[][] matrix, int row, int column) {
		int currentVal = matrix[row][column];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < matrix[row].length; i++) {
			min = Math.min(min, matrix[row][i]);
		}
		if (min != currentVal)
			return false;

		for (int i = 0; i < matrix.length; i++) {
			min = Math.max(min, matrix[i][column]);
		}
		return min == currentVal;
	}
}
