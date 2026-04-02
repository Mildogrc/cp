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
			st = new StringTokenizer(br.readLine());
			long N = Long.parseLong(st.nextToken());
			long K = Long.parseLong(st.nextToken());
			if (N % 2 == 0) {
 
			} else {
				K--;
				N += minVal(N);
 
			}
			System.out.println(N + (K * 2));
		}
	}
 
	public static long minVal(long x) {
		for (long i = 3; i * i <= x; i += 2) {
			if (x % i == 0) {
				return i;
			}
		}
		return x;
	}
}
