import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CF289B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int[][] matrix = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int mod = matrix[0][0] % D;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] % D != mod) {
					System.out.println(-1);
					return;
				}
			}
		}
		boolean[] visited = new boolean[(int) 1e4 + 1];
		long min = Long.MAX_VALUE;
		for (int[] row : matrix) {
			for (int base : row) {
				if (visited[base])
					continue;
				visited[base] = true;
				long sum = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						sum += Math.abs((matrix[i][j] - mod) - (base - mod)) / D;
					}
				}
				min = Long.min(min, sum);
			}
		}
		System.out.println(min);
	}
}
