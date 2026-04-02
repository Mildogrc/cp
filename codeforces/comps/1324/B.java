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
		o: while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[n];
			int[] count = new int[n];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				count[arr[i] - 1]++;
				if (count[arr[i] - 1] > 2) {
					System.out.println("YES");
					continue o;
				}
			}
			System.out.println(possible(arr) ? "YES" : "NO");
		}
	}

	public static boolean possible(int[] arr) {
		Set<Integer> check = new HashSet<>();
//			List<Integer> list = new ArrayList<>();
		check.add(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (check.contains(arr[i]) && arr[i] != arr[i - 1]) {
				return true;
			} else {
				if (arr[i] != arr[i - 1]) {
					check.add(arr[i]);
				}
			}
		}
		return false;
	}
}
