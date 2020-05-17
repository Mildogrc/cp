import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(1 << 20);
		int t = Integer.parseInt(st.nextToken());
		t: while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			long N = Long.parseLong(st.nextToken());
			long K = Long.parseLong(st.nextToken()) - 1;
			while (K-- > 0) {
				int a = minmax(N);
				if (a == 0) {
					sb.append(N + "\n");
					continue t;
				}
				N += a;
			}
			sb.append(N + "\n");
		}
		System.out.println(sb);
	}
 
	public static int minmax(long x) {
		char[] arr = String.valueOf(x).toCharArray();
		int min = arr[0] - '0';
		int max = arr[0] - '0';
		for (char l : arr) {
			if (l == '0') {
				return 0;
			}
			min = Math.min(min, l - '0');
			max = Math.max(max, l - '0');
		}
		return min * max;
	}
}
