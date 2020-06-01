/*
ID: Mildew
LANG: JAVA
TASK: ride
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ride {
	static BufferedReader br;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		setIO("ride");
		String first = br.readLine();
		String second = br.readLine();
		pw.println(score(first) == score(second) ? "GO" : "STAY");
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

	public static int score(String first) {
		int scoreF = 1;
		for (int i = 0; i < first.length(); i++) {
			scoreF *= (first.charAt(i)) - 'A' + 1;
			scoreF %= 47;
		}
		return scoreF;
	}
}
