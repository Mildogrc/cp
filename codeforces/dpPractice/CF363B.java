import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class CF363B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int K = Integer.parseInt(tokens[1]);
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		long sum = 0;
		for (int i = 0; i < K; i++) {
			sum += arr[i];
		}
		long min = sum;
		int ind = 0;
		for (int i = K; i < N; i++) {
			sum += arr[i];
			sum -= arr[i - K];
			if (sum < min) {
				min = sum;
				ind = i - K + 1;
			}
		}
		System.out.println(ind + 1);
	}
}
