import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
 
public class D1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			String s = br.readLine();
			StringBuilder side = new StringBuilder();
			if (s.length() == 1) {
				bw.write(s + "\n");
				continue;
			}
			while (s.charAt(0) == s.charAt(s.length() - 1)) {
				side.append(s.charAt(0));
				s = s.substring(1, s.length() - 1);
				if (s.length() < 2) {
					break;
				}
			}
			String a = palindrone(s);
			String b = palindrone(new StringBuilder(s).reverse().toString());
			bw.write(a.length() > b.length() ? (side.toString() + a + side.reverse().toString() + "\n")
					: (side.toString() + b + side.reverse().toString()) + "\n");
		}
		bw.flush();
	}
 
	public static String palindrone(String s) {
		if (s.length() < 2) {
			return s;
		}
		while (!isPalindrome(s)) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}
 
	public static List<Integer> indeces(String s, char x) {
		List<Integer> index = new ArrayList<>();
		for (int i = s.length() - 1; i > 0; i--) {
			if (s.charAt(i) == x) {
				index.add(i);
			}
		}
		return index;
	}
 
	public static boolean isPalindrome(String s) {
		int lhs = 0;
		int rhs = s.length() - 1;
		while (lhs < rhs) {
			if (s.charAt(lhs) != s.charAt(rhs)) {
				return false;
			}
			lhs++;
			rhs--;
		}
		return true;
	}
}
