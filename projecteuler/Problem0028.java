public class Problem0028 {
	public static void main(String[] args) {
		int N = 1001;
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(arr[i], -1);
		int x = N / 2;
		int y = N / 2;
		int dir = 0;
		for (int i = 1; i < N * N; i++) {
			arr[x][y] = i;
			switch (dir) {
			case 0:
				y++;
				if (x != N - 1 && arr[x + 1][y] == -1) {
					dir = 1;
				}
				break;
			case 1:
				x++;
				if (y != 0 && arr[x][y - 1] == -1) {
					dir = 2;
				}
				break;
			case 2:
				y--;
				if (x != 0 && arr[x - 1][y] == -1) {
					dir = 3;
				}
				break;
			case 3:
				x--;
				if (y != N - 1 && arr[x][y + 1] == -1) {
					dir = 0;
				}
				break;
			}
		}
		arr[0][N - 1] = N * N;
		long sum = -1;
		for (int i = 0; i < N; i++) {
			sum += arr[i][i] + arr[N - i - 1][i];
		}
		System.out.println(sum);
	}
}
