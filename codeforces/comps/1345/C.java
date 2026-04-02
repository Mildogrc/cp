import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		t: while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			System.out.println(works(arr) ? "YES" : "NO");
		}
	}
 
	public static boolean works(int[] arr) {
		int N = arr.length;
		boolean[] check = new boolean[N];
		for (int i = 0; i < N; i++) {
			int ind = (i + arr[i]) % N;
			if (ind < 0) {
				ind += N;
			}
			if (check[ind]) {
				return false;
			}
			check[ind] = true;
		}
		return true;
	}
}
