import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long t = Long.parseLong(st.nextToken());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			long k = Long.parseLong(st.nextToken());
			long[] arr = new long[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}
			int size = (int) max(arr, k);
			if (size == 0) {
				System.out.println("YES");
				continue;
			}
			long[] mod = new long[(size + 1)];
			boolean possible = true;
			for (int i = 0; i < arr.length; i++) {
				if (!required(arr[i], mod, k)) {
					possible = false;
					break;
				}
			}
			if (!possible) {
				System.out.println("NO");
				continue;
			}
			System.out.println(didIt(mod) ? "YES" : "NO");
		}
	}
 
	public static long max(long[] arr, long k) {
		long max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		long count = 0;
		while (max >= k) {
			max /= k;
			count++;
		}
		return count + 1;
	}
 
	public static boolean required(long a, long[] modded, long k) {
		if (a == 0) {
			return true;
		}
		long[] poss = new long[modded.length];
		poss[0] = 1;
		for (int i = 1; i < poss.length; i++) {
			poss[i] = poss[i - 1] * k;
		}
		for (int i = poss.length - 1; i >= 0; i--) {
			if (a >= poss[i]) {
				a -= poss[i];
				modded[i]++;
			}
		}
		return a == 0;
	}
 
	public static boolean didIt(long[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 1) {
				return false;
			}
		}
		return true;
	}
}
