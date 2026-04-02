import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
 
public class E {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Set<Integer> set = new HashSet<>();
			int[] arr = new int[N];
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[i]);
			}
			o: for (int i = 0; i < N - 1; i++) {
				int sum = arr[i] + arr[i + 1];
				set.add(sum);
				for (int j = i + 2; j < N; j++) {
					sum += arr[j];
					set.add(sum);
					if (sum > max) {
						continue o;
					}
				}
			}
			int count = 0;
			for (int x : arr) {
				if (set.contains(x)) {
					count++;
				}
			}
			System.out.println(count);
		}
	}
}
