import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Problem0022 {
	public static void main(String[] args) throws IOException {
		String[] names = readFile("names.txt");
		Arrays.sort(names);
		long sum = 0;
		for (int i = 0; i < names.length; i++) {
			sum += (long) (i + 1) * score(names[i]);
		}
		System.out.println(sum);
	}

	public static long score(String s) {
		long sum = 0;
		for (int j = 0; j < s.length(); j++) {
			sum += s.charAt(j) - 'A' + 1;
		}
		return sum;
	}

	public static String[] readFile(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String s = br.readLine();
		return s.substring(1, s.length() - 1).split("\",\"");
	}
}
