import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CF1278C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(1 << 20);
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] L = new int[N];
			int[] R = new int[N];
			for (int i = N - 1; i >= 0; i--) {
				L[i] = Integer.parseInt(st.nextToken()) - 1;
			}
			for (int i = 0; i < N; i++) {
				R[i] = Integer.parseInt(st.nextToken()) - 1;
			}

			int[] countL = new int[N + 1];
			int[] countR = new int[N + 1];
			countL[N - 1] = (L[N - 1] * 2) - 1;
			countR[N - 1] = (R[N - 1] * 2) - 1;
			for (int i = N - 2; i >= 0; i--) {
				countL[i] = countL[i + 1] + (L[i] * 2) - 1;
				countR[i] = countR[i + 1] + (R[i] * 2) - 1;
			}
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i <= N; i++) {
				if (!map.containsKey(countR[i])) {
					map.put(countR[i], i);
				}
			}
			int min = 2 * N;
			for (int i = 0; i <= N; i++) {
				if (map.containsKey(-countL[i])) {
					min = Math.min(min, map.get(-countL[i]) + i);
				}
			}
			System.out.println(min);
		}
	}
}
