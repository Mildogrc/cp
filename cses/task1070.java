import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class task1070 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N == 1) {
			System.out.println(1);
			return;
		}
		if (N < 4) {
			System.out.println("NO SOLUTION");
			return;
		}
		StringBuilder sb = new StringBuilder();
		int S = N - 1;
		while (S > 0) {
			sb.append(S + " ");
			S -= 2;
		}
		while (N > 0) {
			sb.append(N + " ");
			N -= 2;
		}
		System.out.println(sb);
	}
}
