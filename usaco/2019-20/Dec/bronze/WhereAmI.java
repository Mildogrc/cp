import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WhereAmI {
	public static void main(String[] args) throws IOException {
//		Scanner s = new Scanner(System.in);
		Scanner s = new Scanner(new File("whereami.in")); // <---Change file name
		PrintWriter out = new PrintWriter(new FileWriter("whereami.out")); // <---Change file name
		int N = Integer.parseInt(s.nextLine());
		String mailboxes = s.nextLine();
		String small = longestRepeatedSubstring(mailboxes);
		int val = 0;
		if (allCharactersSame(mailboxes)) {
			val = N;
		} else {
			val = small.length() + 1;
		}
		if(valid(val, mailboxes)) {
			out.println(val);
		}else {
			while(!valid(val,mailboxes)) {
				val++;
			}
			out.println(val);
		}
		out.close();
		s.close();
	}

	public static int[] toInt(String s) {
		String[] tokens = s.split(" ");
		int[] intArray = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			intArray[i] = Integer.parseInt(tokens[i]);
		}
		return intArray;
	}

	public static String longestRepeatedSubstring(String str) {
		int n = str.length();
		int LCSRe[][] = new int[n + 1][n + 1];

		String res = ""; // To store result
		int res_length = 0; // To store length of result

		// building table in bottom-up manner
		int i, index = 0;
		for (i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				// (j-i) > LCSRe[i-1][j-1] to remove
				// overlapping
				if (str.charAt(i - 1) == str.charAt(j - 1) && LCSRe[i - 1][j - 1] < (j - i)) {
					LCSRe[i][j] = LCSRe[i - 1][j - 1] + 1;

					// updating maximum length of the
					// substring and updating the finishing
					// index of the suffix
					if (LCSRe[i][j] > res_length) {
						res_length = LCSRe[i][j];
						index = Math.max(i, index);
					}
				} else {
					LCSRe[i][j] = 0;
				}
			}
		}

		// If we have non-empty result, then insert all
		// characters from first character to last
		// character of String
		if (res_length > 0) {
			for (i = index - res_length + 1; i <= index; i++) {
				res += str.charAt(i - 1);
			}
		}

		return res;
	}

	static boolean allCharactersSame(String s) {
		int n = s.length();
		for (int i = 1; i < n; i++)
			if (s.charAt(i) != s.charAt(0))
				return false;

		return true;
	}
	static boolean valid(int val, String word) {
		List<String> shingles = new ArrayList<>();
		for(int i = 0; i<word.length()-val+1; i++) {
			String shingle = word.substring(i, i+val);
			if(shingles.contains(shingle)) {
				return false;
			}
			shingles.add(shingle);
		}
		return true;
	}
}
