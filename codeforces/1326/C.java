import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int MOD = 998244353;
		String[] tokens = br.readLine().split(" ");
		int n = Integer.parseInt(tokens[0]);
		int k = Integer.parseInt(tokens[1]);
		StringTokenizer st = new StringTokenizer(br.readLine());
		long sum = 0;
		for (int i = 0; i < k; i++) {
			sum += n - i;
		}
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int partitionLength = 0;
		long perm = 1;
		int i = 0;
		while (!isGood(arr[i], n, k)) {
			i++;
		}
		int j = n - 1;
		while (!isGood(arr[j], n, k)) {
			j--;
		}
		for (; i < j + 1; i++) {
			if (isGood(arr[i], n, k)) {
				if (partitionLength != 0) {
					perm *= partitionLength + 1;
					perm %= MOD;
				}
				partitionLength = 0;
			} else {
				partitionLength++;
			}
		}
		System.out.printf("%d %d\n", sum, perm % MOD);
	}
 
	public static boolean isGood(int x, int n, int k) {
		return n - k + 1 <= x && x <= n;
	}
}
