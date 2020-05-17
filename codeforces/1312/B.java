import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] array = new int[n];
			for (int i = 0; i < n; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(array);
			for (int i = 1; i <= n; i++) {
				System.out.print(array[array.length - i] + " ");
			}
			System.out.println();
		}
	}
}
