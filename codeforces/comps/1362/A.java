import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			long[] arr = new long[2];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 2; i++)
				arr[i] = Long.parseLong(st.nextToken());
			long a = Long.max(arr[0], arr[1]);
			long b = Long.min(arr[0], arr[1]);
			if (a == b) {
				System.out.println(0);
				continue;
			}
			if (a % b == 0) {
				long diff = a / b;
				if ((diff & diff - 1) == 0) {
					long sum = 0;
					while (diff >>> 3 > 0) {
						diff >>= 3;
						sum++;
					}
					while (diff >>> 2 > 0) {
						diff >>= 2;
						sum++;
					}
					while (diff >>> 1 > 0) {
						diff >>= 1;
						sum++;
					}
					System.out.println(sum);
				} else {
					System.out.println(-1);
				}
			} else {
				System.out.println(-1);
			}
		}
	}
}
