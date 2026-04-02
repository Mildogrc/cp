import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
 
public class B {
	public static boolean unbroken(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i + 1] > arr[i]) {
				return true;
			}
		}
		return false;
	}
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		t: while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[n];
			int[] count = new int[n];
			int max = 0;
			Set<Integer> set = new HashSet<>();
			Set<Integer> check = new HashSet<>();
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				count[arr[i] - 1]++;
				if (check.contains(arr[i])) {
					System.out.println(0);
					continue t;
				}
				if (set.contains(arr[i])) {
					check.add(arr[i]);
				}
				set.add(arr[i]);
				max = Math.max(arr[i], max);
			}
			if (max != set.size() || check.size() == 0 || unbroken(count)) {
				System.out.println(0);
				continue t;
			}
			Set<Integer> front = new HashSet<>();
			Set<Integer> back = new HashSet<>();
			for (int i = 0; i < max; i++) {
				front.add(arr[i]);
				back.add(arr[arr.length - 1 - i]);
			}
			if (front.size() != max && back.size() != max) {
				System.out.println(0);
				continue t;
			}
			int perm = 0;
			String a = "";
			String b = "";
			if (front.size() == max) {
				perm++;
				a = max + " " + (n - max);
			}
			if (back.size() == max) {
				perm++;
				b = ((n - max) + " " + max);
			}
			if (a.equals(b)) {
				System.out.println(1);
				System.out.println(a);
				continue t;
			}
			System.out.println(perm);
			if (!a.equals("")) {
				System.out.println(a);
			}
			if (!b.equals("")) {
				System.out.println(b);
			}
		}
	}
}
