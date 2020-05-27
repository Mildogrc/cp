public class Problem0018 {
	static String triangle = 
			"75\n" + 
			"95 64\n" + 
			"17 47 82\n" + 
			"18 35 87 10\n" + 
			"20 04 82 47 65\n" + 
			"19 01 23 75 03 34\n" + 
			"88 02 77 73 07 63 67\n" + 
			"99 65 04 28 06 16 70 92\n" + 
			"41 41 26 56 83 40 80 70 33\n" + 
			"41 48 72 33 47 32 37 16 94 29\n" + 
			"53 71 44 65 25 43 91 52 97 51 14\n" + 
			"70 11 33 28 77 73 17 78 39 68 17 57\n" + 
			"91 71 52 38 17 14 91 43 58 50 27 29 48\n" + 
			"63 66 04 68 89 53 67 30 73 16 69 87 40 31\n" + 
			"04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";

	public static void main(String[] args) {
		int[][] arr = toArray(triangle);
		long[][] dp = new long[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			dp[i] = new long[arr[i].length];
		}
		long max = 0;
		dp[0][0] = arr[0][0];
		for (int i = 1; i < dp.length; i++) {
			int jMax = dp[i].length - 1;
			for (int j = 0; j <= jMax; j++) {
				if (j == 0) {
					dp[i][j] = dp[i - 1][j];
				} else if (j == jMax) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
				}
				dp[i][j] += arr[i][j];
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max);
	}

	public static int[][] toArray(String s) {
		String[] tokens = s.split("\n");
		int[][] arr = new int[tokens.length][];
		for (int i = 0; i < tokens.length; i++) {
			String[] splits = tokens[i].split(" ");
			arr[i] = new int[splits.length];
			for (int j = 0; j < splits.length; j++) {
				arr[i][j] = Integer.parseInt(splits[j]);
			}
		}
		return arr;
	}
}
