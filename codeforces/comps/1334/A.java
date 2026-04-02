import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		t: while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[][] plays = new int[n][2];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				plays[i][0] = Integer.parseInt(st.nextToken());
				plays[i][1] = Integer.parseInt(st.nextToken());
			}
			if (plays[0][0] < plays[0][1]) {
				System.out.println("NO");
				continue;
			}
			for (int i = 0; i < n - 1; i++) {
				if (plays[i][0] > plays[i + 1][0] || plays[i][1] > plays[i + 1][1]
						|| plays[i + 1][0] < plays[i + 1][1]) {
					System.out.println("NO");
					continue t;
				}
			}
			for (int i = 1; i < n; i++) {
				int maxPossible = plays[i][0] - plays[i - 1][0];
				int actual = plays[i][1] - plays[i - 1][1];
				if (actual > maxPossible) {
					System.out.println("NO");
					continue t;
				}
			}
			System.out.println("YES");
		}
	}
}
