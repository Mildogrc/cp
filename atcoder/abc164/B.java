ort java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double th = Integer.parseInt(st.nextToken());
		double ts = Integer.parseInt(st.nextToken());
		double ah = Integer.parseInt(st.nextToken());
		double as = Integer.parseInt(st.nextToken());
		System.out.println(Math.ceil(th / as) >= Math.ceil(ah / ts) ? "Yes" : "No");
	}
}
