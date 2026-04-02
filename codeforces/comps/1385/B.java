import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class B {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO();
		int t = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			int N = Integer.parseInt(in.readLine());
			String[] tokens = in.readLine().split(" ");
			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < (N << 1); i++) {
				int a = Integer.parseInt(tokens[i]);
				if (!set.contains(a))
					sb.append(a + " ");
				set.add(a);
			}
			sb.append("\n");
		}
		System.out.println(sb);
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

