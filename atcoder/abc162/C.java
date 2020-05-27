import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		long sum = 0;
		for (int a = 1; a <= K; a++) {
			for (int b = 1; b <= K; b++) {
				for (int c = 1; c <= K; c++) {
					sum += gcd(a, b, c);
				}
			}
		}
		System.out.println(sum);
	}
 
	static int gcd(int a, int b, int c) {
		return gcd(a, gcd(b, c));
	}
 
	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
}
