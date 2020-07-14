import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class task1622 {
	static Set<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s = br.readLine().toCharArray();
		set = new HashSet<>();
		heapPermutation(s, s.length, s.length);
		List<String> list = new ArrayList<>(set);
		list.sort((a, b) -> a.compareTo(b));
		StringBuilder sb = new StringBuilder();
		sb.append(set.size() + "\n");
		for (int i = 0; i < list.size(); i++)
			sb.append(list.get(i) + "\n");
		System.out.println(sb);
	}

	static void heapPermutation(char a[], int size, int n) {
		if (size == 1)
			record(a);

		for (int i = 0; i < size; i++) {
			heapPermutation(a, size - 1, n);
			if (size % 2 == 1) {
				char temp = a[0];
				a[0] = a[size - 1];
				a[size - 1] = temp;
			} else {
				char temp = a[i];
				a[i] = a[size - 1];
				a[size - 1] = temp;
			}
		}
	}

	private static void record(char[] input) {
		set.add(String.valueOf(input));
	}
}
