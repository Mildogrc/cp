import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
 
public class E1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] chars = br.readLine().toCharArray();
		br.close();
		int[] count = new int[26];
		Letter[] letters = new Letter[chars.length];
		for (int i = 0; i < chars.length; i++) {
			Letter letter = new Letter(chars[i], count[charInt(chars[i])]);
			letter.initialIndex = i;
			letters[i] = letter;
			count[charInt(chars[i])]++;
		}
		Letter[] sortedLetters = new Letter[letters.length];
		boolean possible = true;
		System.arraycopy(letters, 0, sortedLetters, 0, letters.length);
		sort(sortedLetters);
		for (int i = 0; i < sortedLetters.length; i++) {
			List<Letter> aversion = new ArrayList<>();
			for (int j = 0; j < sortedLetters.length; j++) {
				if (i == j) {
					continue;
				}
				if (averted(sortedLetters[i], sortedLetters[j], sortedLetters)) {
					aversion.add(sortedLetters[j]);
				}
			}
			if (hasAversion(aversion)) {
				possible = false;
				break;
			} else {
				sortedLetters[i].color = 1;
				setAllto0(aversion);
			}
		}
		if (!possible) {
			System.out.println("NO");
		} else {
			sortBack(sortedLetters);
			String solution = "";
			for (int i = 0; i < sortedLetters.length; i++) {
				solution += sortedLetters[i].color;
			}
			System.out.println("YES");
			System.out.println(solution);
		}
	}
 
	static class Letter {
		char letter;
		int count;
		int initialIndex;
		Integer color;
 
		Letter(char letter, int count) {
			this.letter = letter;
			this.count = count;
		}
 
		boolean equals(Letter anotherLetter) {
			return this.count == anotherLetter.count && this.letter == anotherLetter.letter;
		}
 
		static String toString(Letter letter) {
			String a = "";
			a += letter.letter;
			a += letter.count;
			return a;
		}
	}
 
	public static int charInt(char a) {
		return (int) a - 97;
	}
 
	public static boolean averted(Letter current, Letter toCheck, Letter[] letters) {
		boolean array = indexOf(toCheck, letters) < indexOf(current, letters);
		boolean initial = toCheck.initialIndex < current.initialIndex;
		return array ^ initial;
	}
 
	public static boolean averted(Letter current, Letter toCheck, List<Letter> letters) {
		boolean array = indexOf(toCheck, letters) < indexOf(current, letters);
		boolean initial = toCheck.initialIndex < current.initialIndex;
		return array ^ initial;
	}
 
	public static int indexOf(Letter current, Letter[] letters) {
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].equals(current)) {
				return i;
			}
		}
		return -1;
	}
 
	public static int indexOf(Letter current, List<Letter> letters) {
		for (int i = 0; i < letters.size(); i++) {
			if (letters.get(i).equals(current)) {
				return i;
			}
		}
		return -1;
	}
 
	public static void sort(Letter[] letters) {
		Arrays.sort(letters, new Comparator<Letter>() {
 
			@Override
			public int compare(final Letter entry1, final Letter entry2) {
				int entry1n = charInt(entry1.letter) * 1000 + entry1.count;
				int entry2n = charInt(entry2.letter) * 1000 + entry2.count;
 
				if (entry1n == entry2n) {
					return 0;
				}
				if (entry1n < entry2n) {
					return -1;
				} else {
					return 1;
				}
			}
		});
	}
 
	public static void sortBack(Letter[] letters) {
		Arrays.sort(letters, new Comparator<Letter>() {
 
			@Override
			public int compare(final Letter entry1, final Letter entry2) {
 
				if (entry1.initialIndex < entry2.initialIndex) {
					return -1;
				} else {
					return 1;
				}
			}
		});
	}
 
	public static boolean hasAversion(List<Letter> aversion) {
		for (int i = 0; i < aversion.size(); i++) {
			for (int j = 0; j < aversion.size(); j++) {
				if (j != i) {
					if (averted(aversion.get(i), aversion.get(j), aversion)) {
						return true;
					}
				}
			}
		}
		return false;
	}
 
	public static void setAllto0(List<Letter> letters) {
		for (int i = 0; i < letters.size(); i++) {
			letters.get(i).color = 0;
		}
	}
 
	public static String toString(Letter[] a) {
		if (a == null)
			return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
			return "[]";
 
		StringBuilder b = new StringBuilder();
		b.append('[');
		for (int i = 0;; i++) {
			b.append("" + a[i].letter + "" + a[i].count);
			if (i == iMax)
				return b.append(']').toString();
			b.append(", ");
		}
	}
 
	public static String toString(List<Letter> a) {
		if (a == null)
			return "null";
		int iMax = a.size() - 1;
		if (iMax == -1)
			return "[]";
 
		StringBuilder b = new StringBuilder();
		b.append('[');
		for (int i = 0;; i++) {
			b.append("" + a.get(i).letter + "" + a.get(i).count);
			if (i == iMax)
				return b.append(']').toString();
			b.append(", ");
		}
	}
 
	public static boolean isColored(Letter[] letters) {
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == null) {
				return false;
			}
		}
		return true;
	}
}
