import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class NationalProject {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long n = Long.parseLong(st.nextToken());
			long g = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long m = (n + 1) / 2;
 
			long left = n;
			long right = 1L << 61;
			while (left < right) {
				long mid = (left + right) / 2;
				long gq = (mid / (g + b)) * g;
				gq += Math.min(g, mid % (g + b));
 
				if (gq >= m)
					right = mid;
				else
					left = mid + 1;
			}
			System.out.println(left);
		}
	}
}
