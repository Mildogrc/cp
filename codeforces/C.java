import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class C {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			long N = Long.parseLong(br.readLine());
			long sum = 0;
			long j = 1;
			for (long i = 3; i <= N; i += 2, j++) {
				sum += ((i * i) - ((i - 2) * (i - 2))) * j;
			}
			System.out.println(sum);
		}
	}
}
