import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		if (s.charAt(2) == s.charAt(3) && s.charAt(4) == s.charAt(5)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
