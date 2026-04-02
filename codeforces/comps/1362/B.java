import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		t: while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			Set<Integer> set = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				set.add(arr[i]);
			}
			xor: for (int xor = 1; xor < 1024; xor++) {
				Set<Integer> newSet = new HashSet<>();
				for (int i = 0; i < N; i++) {
					if (newSet.contains(arr[i] ^ xor) || !set.contains(arr[i] ^ xor)) {
						continue xor;
					} else {
						newSet.add(arr[i] ^ xor);
					}
				}
				System.out.println(xor);
				continue t;
			}
			System.out.println(-1);
		}
	}
}
