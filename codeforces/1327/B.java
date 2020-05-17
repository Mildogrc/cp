import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			boolean[] usedPrinces = new boolean[n];
			boolean[] princcesPaired = new boolean[n];
			int[][] marry = new int[n][];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int[] marryOne = new int[Integer.parseInt(st.nextToken())];
				for (int j = 0; j < marryOne.length; j++) {
					marryOne[j] = Integer.parseInt(st.nextToken()) - 1;
				}
				marry[i] = marryOne;
			}
			for (int i = 0; i < marry.length; i++) {
				Arrays.sort(marry[i]);
			}
			o: for (int i = 0; i < marry.length; i++) {
				for (int j = 0; j < marry[i].length; j++) {
					if (!usedPrinces[marry[i][j]]) {
						usedPrinces[marry[i][j]] = true;
						princcesPaired[i] = true;
						continue o;
					}
				}
			}
			int prin = good(princcesPaired);
			if (prin == -1) {
				bw.append("OPTIMAL\n");
			} else {
				bw.append("IMPROVE\n");
				bw.append((prin) + " " + good(usedPrinces) + "\n");
			}
		}
		bw.flush();
	}
 
	public static int good(boolean[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i]) {
				return i + 1;
			}
		}
		return -1;
	}
}
