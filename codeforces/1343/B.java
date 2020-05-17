import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if (n % 2 == 1 || n / 2 % 2 == 1) {
				System.out.println("NO");
				continue;
			}
			System.out.println("YES");
			int sum = 0;
			StringBuilder sb = new StringBuilder();
			int start = 2;
			for (int i = 0; i < n / 2; i++) {
				sb.append(start + " ");
				sum += start;
				start += 2;
			}
			start = 1;
			for (int i = 0; i < (n / 2) - 1; i++) {
				sb.append(start + " ");
				sum -= start;
				start += 2;
			}
			sb.append(sum);
			System.out.println(sb.toString());
		}
	}
}
