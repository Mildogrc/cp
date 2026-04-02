import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(1 << 20);
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			double N = Integer.parseInt(br.readLine()) * 2;
			double side = 1.0 / Math.sin(Math.PI / N);
			System.out.println(Math.sqrt(side * side - 1));
		}
	}
}

