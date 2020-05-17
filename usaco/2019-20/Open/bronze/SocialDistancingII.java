import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SocialDistancingII {
	static BufferedReader br;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		setIO("socdist2");
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(tokenizer.nextToken());
		int[][] arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			int[] array = { Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()) };
			arr[i] = array;
		}
		Arrays.sort(arr, (a, b) -> a[0] - b[0]);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i][1] != arr[i + 1][1]) {
				min = Math.min(min, Math.abs(arr[i][0] - arr[i + 1][0]));
			}
		}
		List<List<int[]>> vals = new ArrayList<>();
		List<int[]> first = new ArrayList<>();
		first.add(arr[0]);
		vals.add(first);
		for (int i = 1; i < n; i++) {
			if (Math.abs(arr[i][0] - arr[i - 1][0]) < min) {
				vals.get(vals.size() - 1).add(arr[i]);
			} else {
				List<int[]> last = new ArrayList<>();
				last.add(arr[i]);
				vals.add(last);
			}
		}
		int count = 0;
		o: for (List<int[]> these : vals) {
			for (int[] arrs : these) {
				if (arrs[1] == 1) {
					count++;
					continue o;
				}
			}
		}
		pw.println(count);
		closeIO();
	}

	public static void setIO() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
	}

	public static void setIO(String file) throws IOException {
		br = new BufferedReader(new FileReader(file + ".in"));
		pw = new PrintWriter(new BufferedWriter(new FileWriter(file + ".out")));
	}

	public static void closeIO() {
		try {
			br.close();
			pw.close();
			System.exit(0);
		} catch (Exception e) {
		}
	}
}

