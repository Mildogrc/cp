import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class F {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			StringBuilder zs = new StringBuilder();
			StringBuilder os = new StringBuilder();
			StringBuilder ts = new StringBuilder();
			for (int i = 0; i <= zero; i++) {
				zs.append('0');
			}
			for (int i = 0; i <= two; i++) {
				ts.append('1');
			}
			if (zero == 0 && one == 0) {
				System.out.println(ts);
				continue;
			}
			if (two == 0 && one == 0) {
				System.out.println(zs);
				continue;
			}
			if (zero == 0 && two == 0) {
				os.append("01");
				for (int i = 0; i < one - 1; i++) {
					os.append(i % 2);
				}
				System.out.println(os);
				continue;
			}
			if (zero == 0) {
				int i = 0;
				for (i = 0; i < (one >> 1); i++) {
					os.append("10");
				}
				if (one != i * 2) {
					ts.append('0');
				}
				System.out.println(os.toString() + ts.toString());
				continue;
			}
			if (two == 0) {
				int i = 0;
				for (i = 0; i < (one >> 1); i++) {
					os.append("10");
				}
				if (one != i * 2) {
					System.out.println('1' + zs.toString() + os.toString());
				} else {
					System.out.println(zs.toString() + os.toString());
				}
				continue;
			}
			if (one % 2 == 0) {
				ts.append('0');
				one--;
			}
			for (int i = 0; i < (one >> 1); i++) {
				os.append("10");
			}
			System.out.println(zs.toString() + os.toString() + ts.toString());
		}
	}
}
