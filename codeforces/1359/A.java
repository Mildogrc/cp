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
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int szOfHand = N / K;
			int maxJoker = Math.min(M, szOfHand);
			M -= maxJoker;
			int maxOther = (int) Math.ceil((double) M / (double) (K - 1));
			sb.append(maxJoker - maxOther);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
