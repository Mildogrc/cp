import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			String[] tokens = br.readLine().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int M = Integer.parseInt(tokens[1]);
			System.out.println(N == 1 ? 0 : (N == 2 ? M : 2 * M));
		}
	}
}
