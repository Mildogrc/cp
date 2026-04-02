import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class D {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int moves = 1;
			int Alice = arr[0];
			int Bob = 0;
			int l = 1;
			int r = N - 1;
			long last = Alice;
			boolean BobsMove = true;
			while (l <= r) {
				long sum = 0;
				if (BobsMove) {
					while (sum < last + 1 && l <= r) {
						sum += arr[r];
						r--;
					}
					Bob += sum;
				} else {
					while (sum < last + 1 && l <= r) {
						sum += arr[l];
						l++;
					}
					Alice += sum;
				}
				last = sum;
				moves++;
				BobsMove = !BobsMove;
			}
			System.out.println(moves + " " + Alice + " " + Bob);
		}
	}
}
