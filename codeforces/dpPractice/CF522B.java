import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CF522B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] width = new int[N];
		int[] height = new int[N];
		int maxH = 0, max2H = 0;
		long sum = 0;
		for (int i = 0; i < N; i++) {
			String[] tokens = br.readLine().split(" ");
			int w = Integer.parseInt(tokens[0]);
			int h = Integer.parseInt(tokens[1]);
			width[i] = w;
			height[i] = h;
			sum += w;
			if (h > maxH) {
				max2H = maxH;
				maxH = h;
			} else if (h > max2H) {
				max2H = h;
			}
		}
		StringBuilder sb = new StringBuilder(1 << 20);
		for (int i = 0; i < N; i++) {
			long h = height[i] == maxH ? max2H : maxH;
			long w = sum - width[i];
			sb.append((h * w) + " ");
		}
		System.out.println(sb);
	}
}
