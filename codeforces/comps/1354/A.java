import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(1 << 20);
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			long A = Integer.parseInt(st.nextToken());
			long B = Integer.parseInt(st.nextToken());
			long C = Integer.parseInt(st.nextToken());
			long D = Integer.parseInt(st.nextToken());
			if (B >= A) {
				sb.append(B + "\n");
				continue;
			}
			if (D >= C) {
				sb.append("-1\n");
				continue;
			}
			long sleep = C - D;
			long need = A - B;
			long cyc = need / sleep;
			cyc += need % sleep == 0 ? 0 : 1;
			cyc *= C;
			sb.append(B + cyc).append("\n");
		}
		System.out.println(sb);
	}
}

