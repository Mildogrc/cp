import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			String[] tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			if (a == 1 || b == 1 || a == 2 && b == 2) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}
}
