import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SocialDistancing {
	static BufferedReader br;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		setIO("socdist");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[][] intervals = new long[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			long[] interval = { Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()) };
			intervals[i] = interval;
		}
		Arrays.sort(intervals, (a, b) -> Long.compare(a[0], b[0]));
		long lhs = 1;
		long rhs = (long) Math.ceil((double) (intervals[intervals.length - 1][1] - intervals[0][0]) / (double) N);
		while (lhs < rhs) {
			long mid = (lhs + rhs + 1) / 2;
			if (works(intervals, mid, N)) {
				lhs = mid;
			} else {
				rhs = mid - 1;
			}
		}
		pw.print(lhs + "\n");
		pw.close();
	}

	public static boolean works(long[][] intervals, long distance, int cows) {
		long start = intervals[0][0];
		for (int i = 1; i < cows - 1; i++) {
			start += distance;
			int ind = where(intervals, start);
			if (!(start >= intervals[ind][0] && start <= intervals[ind][1])) {
				if (ind + 1 >= intervals.length) {
					return false;
				}
				start = intervals[ind + 1][0];
			}
		}
		return true;
	}

	public static int where(long[][] intervals, long coord) {
		int lhs = 0;
		int rhs = intervals.length - 1;
		while (lhs < rhs) {
			int mid = (lhs + rhs + 1) / 2;
			if (intervals[mid][0] < coord) {
				lhs = mid;
			} else {
				rhs = mid - 1;
			}
		}
		return lhs;
	}

	public static void setIO() throws IOException {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			pw = new PrintWriter(System.out);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void setIO(String s) throws IOException {
		try {
			br = new BufferedReader(new FileReader(s + ".in"));
			pw = new PrintWriter(new BufferedWriter(new FileWriter(s + ".out")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
