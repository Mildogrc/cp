import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class C {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO();
		int t = Integer.parseInt(in.readLine());
		t: while (t-- > 0) {
			int N = Integer.parseInt(in.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			boolean flip = false;
			for (int i = N - 1; i > 0; i--) {
				if (flip) {
					if (arr[i] < arr[i - 1]) {
						out.println(i);
						continue t;
					}
				} else {
					if (arr[i] > arr[i - 1]) {
						flip = true;
						i++;
					}
				}
			}
			out.println(0);
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
}

