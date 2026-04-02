import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
public class B {
 
	public static BufferedReader br;
	public static PrintWriter pw;
	public static StringTokenizer tok;
 
	public static void main(String[] args) throws IOException {
		setIO();
		int t = Integer.parseInt(br.readLine());
		int maxN = 25819;
		while (t-- > 0) {
			int count = 0;
			int n = Integer.parseInt(br.readLine());
			while (n > 1) {
				int lhs = 1, rhs = maxN;
				while (lhs < rhs) {
					int mid = lhs + ((rhs - lhs + 1) >> 1);
					if (cards(mid) <= n) {
						lhs = mid;
					} else {
						rhs = mid - 1;
					}
				}
				n -= cards(lhs);
				count++;
			}
			System.out.println(count);
		}
 
	}
 
	static int cards(int n) {
		int count = (n * (n + 1)) >> 1;
		return (3 * count) - n;
	}
 
	public static void setIO() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
 
}
