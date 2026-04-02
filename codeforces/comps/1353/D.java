import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
 
public class D {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
                StringBuilder sb = new StringBuilder(1 << 20);
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			Queue<int[]> bfs = new PriorityQueue<>((a, b) -> {
				if (a[1] - a[0] == b[1] - b[0]) {
					return Integer.compare(a[0], b[0]);
				}
				return -Integer.compare(a[1] - a[0], b[1] - b[0]);
			});
			int[] arr = new int[N];
			int time = 1;
			bfs.add(new int[] { 0, N - 1 });
			while (bfs.size() > 0) {
				int[] sub = bfs.poll();
                                if(sub[0] > sub[1]) continue;
				if (sub[0] == sub[1]) {
					arr[sub[0]] = time++;
					continue;
				}
				int mid = (sub[0] + sub[1]) >> 1;
				arr[mid] = time++;
				bfs.add(new int[] { sub[0], mid - 1 });
				bfs.add(new int[] { mid + 1, sub[1] });
			}
			for (int i = 0; i < N; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
		}
                System.out.println(sb);
	}
}
