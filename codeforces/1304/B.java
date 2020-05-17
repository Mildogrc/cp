import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int n = Integer.parseInt(tokens[0]);
		List<String> shingles = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			shingles.add(br.readLine());
		}
		String beginning = "";
		List<String> shinglesT = new ArrayList<>();
		for (int i = 0; i < shingles.size();) {
			if (equalsReverse(shingles.get(i))) {
				shinglesT.add(shingles.get(i));
				shingles.remove(i);
			} else {
				i++;
			}
		}
		for (int i = 0; i < shingles.size();) {
			int a = shingles.indexOf(reverse(shingles.get(i)));
			if (a != -1) {
				beginning += shingles.get(i);
				shingles.remove(a);
				shingles.remove(0);
			} else {
				i++;
			}
		}
		String base = baseStr(shinglesT);
		int a = beginning.length() * 2 + base.length();
		System.out.println(a);
		System.out.println(beginning + base + reverse(beginning));
	}
 
	public static String reverse(String s) {
		String reverse = "";
		for (int i = 1; i <= s.length(); i++) {
			reverse += s.charAt(s.length() - i);
		}
		return reverse;
	}
 
	public static String baseStr(List<String> list) {
		String beginning = "";
		String mid = "";
		if (list.size() == 0) {
			return "";
		} else if (list.size() == 1) {
			return list.get(0);
		} else {
			for (int i = 0; i < list.size(); i++) {
				int a = list.lastIndexOf(list.get(i));
				if (a == i) {
					if (mid.length() == 0) {
						mid = list.get(i);
					}
				} else {
					beginning += list.get(i);
					list.remove(a);
					list.remove(0);
				}
			}
			return beginning + mid + beginning;
		}
	}
 
	public static boolean equalsReverse(String s) {
		return s.equals(reverse(s));
	}
}
