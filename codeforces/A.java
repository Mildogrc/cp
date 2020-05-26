import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			String[] tokens = br.readLine().split(" ");
			int n = Integer.parseInt(tokens[0]);
			int m = Integer.parseInt(tokens[1]);
			int sum = n * (m >> 1);
			if ((m >> 1 << 1) != m) {
				sum += (n + 1) >> 1;
			}
			System.out.println(sum);
		}
	}
}
