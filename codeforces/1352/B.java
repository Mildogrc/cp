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
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			StringBuilder sb = new StringBuilder();
			if ((N % 2 == 1 && K % 2 == 0) || K > N) {
				System.out.println("NO");
				continue;
			}
			if (N == K) {
				for (int i = 0; i < N; i++) {
					sb.append(1).append(" ");
				}
				System.out.println("YES");
				System.out.println(sb);
				continue;
			}
			if (N % 2 == K % 2) {
				for (int i = 0; i < K - 1; i++) {
					sb.append(1).append(" ");
				}
				sb.append((N - K + 1));
				System.out.println("YES");
				System.out.println(sb);
				continue;
			}
			if (N % 2 == 0 && K % 2 == 1) {
				if ((K - 1) * 2 < N) {
					System.out.println("YES");
					for (int i = 0; i < K - 1; i++) {
						sb.append(2).append(" ");
					}
					sb.append(N - (K - 1) * 2);
					System.out.println(sb);
				} else {
					System.out.println("NO");
				}
				continue;
			}
 
		}
	}
}
