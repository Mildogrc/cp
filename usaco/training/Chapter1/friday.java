/*
ID: Mildew
LANG: JAVA
TASK: friday
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class friday {
	static BufferedReader br;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		setIO("friday");
		int N = Integer.parseInt(br.readLine());
		int[] freq = new int[7];
		int day = 2;
		day += 12;
		day %= 7;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 12; j++) {
				freq[day]++;
				day += day(1900 + i, j);
				day %= 7;
			}
		}
		StringBuilder sb = new StringBuilder(1 << 5);
		for (int i = 0; i < 7; i++)
			sb.append(freq[i] + " ");
		pw.println(sb.toString().trim());
		pw.close();
	}

	public static String map(int day) {
		String[] days = { "SATURDAY", "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" };
		return days[day];
	}

	public static int day(int year, int month) {
		int[] map = { 31, 30, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (month == 1) {
			if (year % 400 == 0)
				return 29;
			else if (year % 100 == 0)
				return 28;
			else if (year % 4 == 0)
				return 29;
			else
				return 28;
		} else {
			return map[month];
		}
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
