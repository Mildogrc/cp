import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class task1755 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s = br.readLine().toCharArray();
		int[] count = new int[26];
		for (int i = 0; i < s.length; i++)
			count[s[i] - 'A']++;

		boolean odd = false;
		String mid = "";
		for (int i = 0; i < 26; i++) {
			if ((count[i] & 1) == 1) {
				if (odd) {
					System.out.println("NO SOLUTION");
				} else {
					mid = String.valueOf((char) (i + 'A'));
					odd = true;
				}
			}
		}

		StringBuilder left = new StringBuilder();
		for (int i = 0; i < 26; i++)
			for (int j = 0; j < (count[i] >> 1); j++)
				left.append((char) (i + 'A'));
		StringBuilder right = new StringBuilder();
		for (int i = left.length() - 1; i >= 0; i--)
			right.append(left.charAt(i));// reverse not working on cses??
		System.out.println(left.toString() + mid + right.toString());

	}
}
