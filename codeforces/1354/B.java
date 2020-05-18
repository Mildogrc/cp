import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(1 << 20);
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			String s = br.readLine();
			int n = s.length();
			int size = n;
			int[] ind = new int[3];
			Arrays.fill(ind, -1);
			for (int i = 0; i < n; i++) {
				ind[s.charAt(i) - '1'] = i;
				if (min(ind) != -1) {
					size = min(size, max(ind) - min(ind) + 1);
				}
			}
			sb.append(min(ind) == -1 ? 0 : size).append("\n");
		}
		System.out.println(sb);
	}
 
	static int min(int... a) {
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			min = Integer.min(min, a[i]);
		}
		return min;
	}
 
	static int max(int... a) {
		int max = a[0];
		for (int i = 1; i < a.length; i++) {
			max = Integer.max(max, a[i]);
		}
		return max;
	}
}

