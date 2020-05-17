import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class SocialDistancingI {
	static BufferedReader br;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		setIO("socdist1");
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		String[] tokens;
		if (s.charAt(s.length() - 1) == '1') {
			tokens = (s + " ").split("1");
		} else {
			tokens = s.split("1");
		}
		System.out.println(Arrays.toString(tokens));
		if (tokens.length == 1) {
			pw.println(n - 1);
			closeIO();
		}
		if (tokens.length == 2) {
			pw.println(Math.min(tokens[0].length(), tokens[1].length()));
			closeIO();
		}
		int max = 0;
		int[] newTok = new int[tokens.length];
		for (int i = 1; i < tokens.length - 1; i++) {
			newTok[i] = (tokens[i].length() + 1) / 2;
			max = Math.max(max, tokens[i].length());
		}
		newTok[0] = tokens[0].length();
		newTok[newTok.length - 1] = tokens[newTok.length - 1].length();
		Arrays.sort(newTok);
		System.out.println(Arrays.toString(newTok));
		int large = newTok[newTok.length - 2];
		max = ((max + 1) / 3);
		System.out.println("fdasjkl" + ((max - 1) / 3) + " " + large);
		pw.println(Math.max(max, large));

		closeIO();

	}

	public static void setIO() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
	}

	public static void setIO(String file) throws IOException {
		try {
			br = new BufferedReader(new FileReader(file + ".in"));
			pw = new PrintWriter(new BufferedWriter(new FileWriter(file + ".out")));
		} catch (Exception e) {
		}
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
