import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RecordBreaker {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO();
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine());
			int[] arr = new int[N + 1];
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			arr[N] = -1;
			int max = -1;
			int count = 0;
			for (int i = 0; i < N; i++) {
				if (arr[i] > max && arr[i] > arr[i + 1]) {
					count++;
				}
				max = Math.max(max, arr[i]);
			}
			System.out.printf("Case #%d: %d\n", t, count);
		}
		out.close();
	}

	public static void setIO() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
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
