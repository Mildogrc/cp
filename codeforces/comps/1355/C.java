import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		long sum = 0;
		int depthsz = Integer.min(B - A + 1, C - B + 1);
		int N = C - A + 1;
		for (int i = 0; i < N; i++) {
			long count = Integer.min(Integer.min(i + 1, depthsz), N - i);
			int sumOfSides = A + B + i;
			long triangles = Integer.min(Integer.max(0, sumOfSides - C), D - C + 1);
			sum += (count * triangles);
		}
		System.out.println(sum);
	}
 
}
