import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class B {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO();
		int t = Integer.parseInt(in.readLine());
		o: while (t-- > 0) {
			int N = Integer.parseInt(in.readLine());
			for (int i = 2; i * i <= N; i++) {
				if (N % i == 0) {
					int a = N / i;
					out.println(a + " " + (N - a));
					continue o;
				}
			}
			out.println("1 " + (N - 1));
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
