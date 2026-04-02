import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class C {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			long N = Long.parseLong(st.nextToken());
			long K = Long.parseLong(st.nextToken());
			long ret = K + (K / (N - 1));
			if (K % (N - 1) == 0) {
				ret--;
			}
			System.out.println(ret);
		}
	}
}
