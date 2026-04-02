import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int N = 2;
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int min = Math.max(Math.max(arr[0], arr[1]), Math.min(arr[0] * 2, arr[1] * 2));
			sb.append(min * min);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
