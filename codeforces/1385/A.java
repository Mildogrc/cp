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
			String[] tokens = in.readLine().split(" ");
			int x = Integer.parseInt(tokens[0]);
			int y = Integer.parseInt(tokens[1]);
			int z = Integer.parseInt(tokens[2]);
			if (x != y && y != z && x != z) {
				out.println("NO");
			} else {
				if (x == y && y == z) {
					out.println("YES");
					out.printf("%d %d %d\n", x, x, x);
				} else if (x == y && x > z) {
					out.println("YES");
					out.printf("%d %d %d\n", x, 1, z);
				} else if (x == z && x > y) {
					out.println("YES");
					out.printf("%d %d %d\n", x, y, 1);
				} else if (y == z && y > x) {
					out.println("YES");
					out.printf("%d %d %d\n", x, 1, z);
				} else {
					out.println("NO");
				}
			}
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

