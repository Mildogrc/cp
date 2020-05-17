import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			String[] tokens = br.readLine().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int K = Integer.parseInt(tokens[1]);
			st = new StringTokenizer(br.readLine());
			Integer[] a = new Integer[N];
			Integer[] b = new Integer[N];
			long sum = 0;
			for (int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
				sum += a[i];
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(a);
			Arrays.sort(b, (x, y) -> y - x);
			for (int i = 0; i < K; i++) {
				int x = b[i] - a[i];
				if (x <= 0) {
					break;
				}
				sum += x;
			}
			System.out.println(sum);
		}
	}
}
