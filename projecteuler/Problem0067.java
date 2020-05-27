import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Problem0067 {
	public static void main(String[] args) throws IOException {
		String triangle = createString();
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

	public static String createString() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("triangle.txt"));
		StringBuilder sb = new StringBuilder(1 << 20);
		String s = br.readLine();
		while (s != null) {
			sb.append(s);
			s = br.readLine();
			sb.append("\n");
		}
		br.close();
		return sb.toString();
	}
}
