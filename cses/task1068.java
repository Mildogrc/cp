import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class task1068 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		long N = Integer.parseInt(br.readLine());
		while (N != 1) {
			sb.append(N + " ");
			N = next(N);
		}
		System.out.println(sb.toString() + "1");
	}

	public static long next(long x) {
		return x % 2 == 0 ? x >> 1 : 3 * x + 1;
	}
}
