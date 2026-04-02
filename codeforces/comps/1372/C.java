import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class C {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO();
		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
			int N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine());

			int[] arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			List<Integer> spots = spots(arr);
			out.println(Math.min(spots.size(), 2));
		}
		out.close();

	}

	static List<Integer> spots(int[] arr) {
		int len = 0;
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != i + 1) {
				len++;
			} else {
				if (len != 0)
					ans.add(len);
				len = 0;
			}
		}
		if (len != 0)
			ans.add(len);
		return ans;
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
