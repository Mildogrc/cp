import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NavigableSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(1 << 20);
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			String[] tokens = br.readLine().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int K = Integer.parseInt(tokens[1]);
			st = new StringTokenizer(br.readLine());
			NavigableSet<Integer> set = new TreeSet<>();
			for (int i = 0; i < N; i++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
			if (set.size() > K) {
				sb.append("-1\n");
				continue;
			}
			sb.append(N * K + "\n");
			StringBuilder s = new StringBuilder();
			for (int x : set) {
				s.append(x + " ");
			}
			int num = 1;
			for (int i = 0; i < K - set.size(); i++) {
				while (set.contains(num)) {
					num++;
				}
				s.append(num + " ");
			}
			for (int i = 0; i < N; i++) {
				sb.append(s.toString());
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
