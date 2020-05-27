import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		double[] arr = new double[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			arr[i] /= K;
		}
		double max = 1.0 + arr[0] - arr[N - 1];
		for (int i = 0; i < N - 1; i++) {
			max = Double.max(max, arr[i + 1] - arr[i]);
		}
		System.out.println(Math.round((1 - max) * K));
	}
}
