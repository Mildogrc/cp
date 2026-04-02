import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
outerloop:while (t-- > 0) {
			char[] a = br.readLine().toCharArray();
			char[] b = br.readLine().toCharArray();
			char[] c = br.readLine().toCharArray();
			for(int i = 0; i<c.length; i++) {
				if(c[i] != a[i] && c[i] != b[i]) {
					System.out.println("NO");
					continue outerloop;
				}
			}
			System.out.println("YES");
		}
	}
}
