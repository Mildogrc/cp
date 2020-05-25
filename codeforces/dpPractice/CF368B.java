import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class CF368B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Set<Integer> set = new HashSet<>();
		set.add(arr[N - 1]);
		arr[N - 1] = 1;
		for (int i = N - 2; i >= 0; i--) {
			int t = arr[i];
			arr[i] = arr[i + 1];
			arr[i] += set.contains(t) ? 0 : 1;
			set.add(t);
		}
		StringBuilder sb = new StringBuilder(1 << 20);
		for (int i = 0; i < M; i++) {
			sb.append(arr[Integer.parseInt(br.readLine()) - 1]).append("\n");
		}
		System.out.println(sb);
	}
}
