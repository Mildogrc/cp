import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class G {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(1 << 20);
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			if (N < 4) {
				sb.append("-1\n");
				continue;
			}
			if (N == 4) {
				sb.append("3 1 4 2\n");
				continue;
			}
			if (N == 5) {
				sb.append("3 1 4 2 5\n");
				continue;
			}
			int n = N;
			N = N >> 1 << 1;
			while (N > 6) {
				sb.append(N + " ");
				N -= 2;
			}
			N = 7;
			sb.append("6 3 1 4 2 5 ");
			while (N <= n) {
				sb.append(N + " ");
				N += 2;
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
