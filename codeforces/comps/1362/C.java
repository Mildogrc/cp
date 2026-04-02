import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			long N = Long.parseLong(br.readLine());
			long sum = 0;
			while (N > 0) {
				long lsb = N & -N;
				sum += lsb * 2 - 1;
				N -= lsb;
			}
			System.out.println(sum);
		}
	}
}
