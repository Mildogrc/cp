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

public class Cereal {
	static BufferedReader br;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		setIO("cereal");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<int[]>[] choice = new List[M];
		int[][] mem = new int[N][];
		for (int i = 0; i < choice.length; i++) {
			choice[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int[] val = { i, 0 };
			int second = Integer.parseInt(st.nextToken()) - 1;
			int[] val2 = { i, 1 };
			choice[first].add(val);
			if (choice[first].size() != 1) {
				choice[second].add(val2);
			}
			int[] pref = { first, second };
			mem[i] = pref;
		}
		int minPos = 0;
		for (int i = 0; i < choice.length; i++) {
			if (choice[i].size() != 0) {
				minPos++;
			}
		}
		int[] possible = new int[N];
		possible[0] = minPos;
		for (int i = 0; i < N; i++) {
			pw.println(minPos);
			int first = mem[i][0];
			choice[first].remove(0);
			if (choice[first].size() == 0) {
				minPos--;
				continue;
			}
			if (choice[first].get(0)[1] == 1) {
				continue;
			}
			while (choice[first].size() > 0 || choice[first].get(0)[0] == 0) {
				int otherPref = mem[choice[first].get(0)[0]][1];
				int ind = search(choice[otherPref], choice[first].get(0)[0]);
				if (ind == 0) {
					choice[otherPref].remove(0);
					if (choice[otherPref].size() == 0) {
						minPos--;
						break;
					}
					if (choice[otherPref].get(0)[1] == 1) {
						break;
					}
					first = mem[choice[first].get(0)[0]][1];
				} else {
					choice[otherPref].remove(ind);
					break;
				}
			}
		}
		pw.close();
	}

	public static int search(List<int[]> choice, int val) {
		int lhs = 0;
		int rhs = choice.size() - 1;
		while (lhs < rhs) {
			int mid = (lhs + rhs + 1) / 2;
			if (choice.get(mid)[0] == val) {
				return mid;
			}
			if (choice.get(mid)[0] < val) {
				lhs = mid;
			} else {
				rhs = mid - 1;
			}
		}
		return lhs;
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
