import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CF1108D {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] lamps = br.readLine().toCharArray();
		int[][] dp = new int[N][3];
		dp[0][0] = dp[0][1] = dp[0][2] = 1;
		switch (lamps[0]) {
		case 'R':
			dp[0][0] = 0;
			break;
		case 'G':
			dp[0][1] = 0;
			break;
		case 'B':
			dp[0][2] = 0;
			break;
		}
		for (int i = 1; i < N; i++) {
			switch (lamps[i]) {
			case 'R':
				dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]);
				dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + 1;
				dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + 1;
				break;
			case 'G':
				dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + 1;
				dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]);
				dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + 1;
				break;
			case 'B':
				dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + 1;
				dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + 1;
				dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]);
				break;
			}
		}
		char[] ret = new char[N];
		int ind = 0;
		{
			int min = min(dp[N - 1][0], min(dp[N - 1][1], dp[N - 1][2]));
			System.out.println(min);
			if (dp[N - 1][0] == min) {
				ret[N - 1] = 'R';
				ind = 0;
			} else if (dp[N - 1][1] == min) {
				ret[N - 1] = 'G';
				ind = 1;
			} else {
				ret[N - 1] = 'B';
				ind = 2;
			}
		}
		for (int i = N - 2; i >= 0; i--) {
			if (ind == 0) {
				if (dp[i][1] < dp[i][2]) {
					ret[i] = 'G';
					ind = 1;
				} else {
					ret[i] = 'B';
					ind = 2;
				}
			} else if (ind == 1) {
				if (dp[i][0] < dp[i][2]) {
					ret[i] = 'R';
					ind = 0;
				} else {
					ret[i] = 'B';
					ind = 2;
				}
			} else {
				if (dp[i][0] < dp[i][1]) {
					ret[i] = 'R';
					ind = 0;
				} else {
					ret[i] = 'G';
					ind = 1;
				}
			}
		}
		System.out.println(String.valueOf(ret));
	}
}
