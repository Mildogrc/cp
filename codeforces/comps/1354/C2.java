import static java.lang.Math.sin;
import static java.lang.Math.PI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			double N = Integer.parseInt(br.readLine()) * 2;
			double side = 1.0 / sin(PI / N);
			double angle = PI - ((PI + PI / N) / 2);
			System.out.println(side * sin(angle));
		}
	}
}
