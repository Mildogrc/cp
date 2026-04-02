import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class D {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO();
		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
			int N = Integer.parseInt(in.readLine());
			String s = in.readLine();
			out.println(cost(s, 0, N - 1, 'a'));
		}
		out.close();
	}

	static int cost(String s, int start, int finish, char c) {
		if (start == finish)
			if (s.charAt(start) == c)
				return 0;
			else
				return 1;
		int mid = (start + finish) >> 1;
		int first = costToMakec(s, start, mid, c) + cost(s, mid + 1, finish, (char) (c + 1));
		int second = costToMakec(s, mid + 1, finish, c) + cost(s, start, mid, (char) (c + 1));
		return Math.min(first, second);
	}

	static int costToMakec(String s, int start, int finish, char c) {
		int count = 0;
		for (int i = start; i <= finish; i++) {
			if (s.charAt(i) == c)
				count++;
		}
		return finish - start - count + 1;
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

