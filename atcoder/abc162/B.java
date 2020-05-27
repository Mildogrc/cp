import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		long sum = sum(n);
		long tsum = 3 * sum(n / 3);
		long fsum = 5 * sum(n / 5);
		long ffsum = 15 * sum(n / 15);
		System.out.println(sum - tsum - fsum + ffsum);
	}
 
	public static long sum(long n) {
		return (n * (n + 1)) / 2;
	}
}
