/*
ID: Mildew
LANG: JAVA
TASK: gift1
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class gift1 {
	static BufferedReader br;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		setIO("gift1");
		int N = Integer.parseInt(br.readLine());
		String[] names = new String[N];
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			names[i] = br.readLine();
			map.put(names[i], 0);
		}
		while (true) {
			String giver = br.readLine();
			if (giver == null || giver == "")
				break;
			String[] tokens = br.readLine().split(" ");
			int amount = Integer.parseInt(tokens[0]);
			int people = Integer.parseInt(tokens[1]);
			if (people != 0) {
				map.put(giver, map.get(giver) - amount + (amount % people));
				amount /= people;
			} else {
				continue;
			}
			for (int i = 0; i < people; i++) {
				String name = br.readLine();
				map.put(name, map.get(name) + amount);
			}
//			System.out.println(map);
		}
		for (int i = 0; i < N; i++) {
			pw.println(names[i] + " " + (map.get(names[i])));
		}
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
