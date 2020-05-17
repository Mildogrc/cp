import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			long x = Integer.parseInt(st.nextToken());
			int b1 = find(x);
			int b2 = (int) (x - sum(b1) - 1);
			char[] sb = new char[n];
			for (int i = 0; i < sb.length; i++) {
				sb[i] = 'a';
			}
			sb[b1] = 'b';
			sb[b2] = 'b';
			for (int i = n - 1; i >= 0; i--) {
				bw.write(sb[i]);
			}
			bw.write("\n");
		}
		bw.flush();
	}
 
	public static long sum(long n) {
		return (n * (n - 1)) / 2;
	}
 
	public static int find(long n) {
		long lhs = 0;
		long rhs = (int) (2 * 10e9);
		while (lhs < rhs) {
			long mid = (lhs + rhs + 1) / 2;
			if (sum(mid) < n) {
				lhs = mid;
			} else {
				rhs = mid - 1;
			}
		}
		return (int) lhs;
	}
}
