import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SwapitySwap {
	static BufferedReader br;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		setIO("swap");
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
		int K = Integer.parseInt(tokens[2]);
		int[] to = new int[N + 1];
		int[][] pos = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			to[i] = i;
		}

		for (int i = 0; i < M; i++) {
			tokens = br.readLine().split(" ");
			int l = Integer.parseInt(tokens[0]), r = Integer.parseInt(tokens[1]);
			while (l < r) {
				int t = to[l];
				to[l++] = to[r];
				to[r--] = t;
			}
		}

		boolean[] visited = new boolean[N + 1];
		List<List<Integer>> Map = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				List<Integer> cycle = new ArrayList<>();
				int node = i;
				while (!visited[node]) {
					pos[node] = new int[] { Map.size(), cycle.size() };
					cycle.add(node);
					visited[node] = true;
					node = to[node];
				}
				Map.add(cycle);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			List<Integer> cycle = Map.get(pos[i][0]);
			sb.append(cycle.get((pos[i][1] + K) % cycle.size()));
			sb.append("\n");
		}
		pw.print(sb);
		pw.close();
	}

	public static void setIO() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			pw = new PrintWriter(System.out);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void setIO(String s) {
		try {
			br = new BufferedReader(new FileReader(s + ".in"));
			pw = new PrintWriter(new BufferedWriter(new FileWriter(s + ".out")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
