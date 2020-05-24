import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class D {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			List<Integer> factors = new ArrayList<>();
			for (int i = 1; i * i <= N; i++) {
				if (N % i == 0) {
					factors.add(i);
					factors.add(N / i);
				}
			}
			factors.sort((a, b) -> a - b);
			int lhs = 0, rhs = factors.size() - 1;
			while (lhs < rhs) {
				int mid = lhs + ((rhs - lhs + 1) >> 1);
				if (factors.get(mid) <= K) {
					lhs = mid;
				} else {
					rhs = mid - 1;
				}
			}
			sb.append(N / factors.get(lhs));
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
