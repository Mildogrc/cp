import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		t: while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[101];
			for (int i = 0; i < n; i++) {
				int val = Integer.parseInt(st.nextToken());
				arr[val - 1] = 1;
			}
			int i = 0;
			while (x > 0) {
				if (arr[i] != 1) {
					x--;
				}
				i++;
				if (i == 101) {
					System.out.println(i + x);
					continue t;
				}
			}
			while (i < arr.length && arr[i] == 1) {
				i++;
			}
			System.out.println(i);
		}
	}
}
