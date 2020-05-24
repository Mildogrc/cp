import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			int even = 0;
			int odd = 0;
			int con = 0;
			for (int i = 0; i < N - 1; i++) {
				if (arr[i] % 2 == 0) {
					even++;
				} else {
					odd++;
				}
				if (arr[i] + 1 == arr[i + 1]) {
					con++;
				}
			}
			if (arr[N - 1] % 2 == 0)
				even++;
			else
				odd++;
			even = even % 2;
			odd = odd % 2;
			boolean possible = true;
			if (even == 1) {
				possible = con > 0;
			}
			sb.append(possible ? "YES" : "NO");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
