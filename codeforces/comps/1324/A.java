import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[n];
			int max = Integer.MAX_VALUE;
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				max = Math.min(max, arr[i]);
			}
			for (int i = 0; i < arr.length; i++) {
				arr[i] -= max;
			}
			System.out.println(isPossible(arr) ? "YES" : "NO");
		}
	}
 
	public static boolean isPossible(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 1) {
				return false;
			}
		}
		return true;
	}
 
}
