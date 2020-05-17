import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine(), "R");
			int max = 0;
			while (st.hasMoreTokens()) max = Math.max(max, st.nextToken().length());
			System.out.println(max + 1);
		}
	}
}
