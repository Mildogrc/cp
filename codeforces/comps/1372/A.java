import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class A {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO();
		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
			int N = Integer.parseInt(in.readLine());
			for (int i = 0; i < N; i++)
				out.print("69 ");
			out.println();
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
