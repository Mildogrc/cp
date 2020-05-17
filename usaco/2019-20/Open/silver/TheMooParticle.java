import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TheMooParticle {
	static BufferedReader br;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		setIO("moop");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[][] vals = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int[] val = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			vals[i] = val;
		}
		List<Integer>[] adj = new List[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (vals[i][0] <= vals[j][0] && vals[i][1] <= vals[j][1]
					|| vals[j][0] <= vals[i][0] && vals[j][1] <= vals[i][1]) {
					adj[i].add(j);
					adj[j].add(i);
				}
			}
		}
		int cuts = 0;
		boolean[] visited = new boolean[n];
		int val = firstNonVisited(visited);
		while (val >= 0) {
			visited[val] = true;
			List<Integer> bfs = new ArrayList<>();
			bfs.add(val);
			while (bfs.size() > 0) {
				List<Integer> aux = new ArrayList<>();
				for (int node : bfs) {
					for (int child : adj[node]) {
						if (!visited[child]) {
							aux.add(child);
							visited[child] = true;
						}
					}
				}
				bfs = new ArrayList<>(aux);
			}
			val = firstNonVisited(visited);
			cuts++;
		}
		pw.print(cuts + "\n");
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

	public static int firstNonVisited(boolean[] visited) {
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				return i;
			}
		}
		return -1;
	}
}

