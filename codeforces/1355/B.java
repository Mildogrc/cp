import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(1 << 20);
		int t = Integer.parseInt(st.nextToken());
		t: while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Integer[] arr = new Integer[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			int groups = 0;
			int l = 0;
			while (l < N) {
				int size = 0;
				int max = arr[l];
				while (l < N && size < max) {
					size++;
					max = Math.max(max, arr[l]);
					l++;
				}
				if (size == max) {
					groups++;
				} else {
					break;
				}
			}
			sb.append(groups).append("\n");
		}
		System.out.println(sb);
	}
}
