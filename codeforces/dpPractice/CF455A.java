import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CF455A {
	static BufferedReader in;
	static PrintWriter out;

	static int MAXA = (int) 1e5 + 1;

	public static void main(String[] args) throws IOException {
		setIO();

		long[] freq = new long[MAXA];

		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());

		for (int i = 0; i < N; i++)
			freq[Integer.parseInt(st.nextToken())]++;

		long[] dp = new long[MAXA];
		dp[1] = freq[1];

		for (int i = 2; i < MAXA; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + freq[i] * (long) i);
		}
		out.println(dp[MAXA - 1]);
		out.close();
	}

	public static void setIO() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void setIO(String s) {
		try {
			in = new BufferedReader(new FileReader(s + ".in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter(s + ".out")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
