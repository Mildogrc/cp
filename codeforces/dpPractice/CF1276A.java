import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class CF1276A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder(1 << 20);
		while (t-- > 0) {
			String s = br.readLine();
			if (s.length() < 3 || !s.contains("one") && !s.contains("two")) {
				sb.append("0\n\n");
				continue;
			}
			char[] word = s.toCharArray();
			int N = word.length;
			Set<Integer> indexs = new HashSet<>();
			for (int i = 0; i < N; i++) {
				if ((i + 3 <= N && s.substring(i, i + 3).equals("one"))
						&& (i - 2 >= 0 && s.substring(i - 2, i + 1).equals("two"))) {
					indexs.add(i);
				} else if (i + 3 <= N && s.substring(i, i + 3).equals("one")) {
					indexs.add(i + 1);
				} else if (i - 2 >= 0 && s.substring(i - 2, i + 1).equals("two")) {
					for (int j = i; j < N && s.charAt(j) == 'o'; j++) {
						indexs.add(i - 1);
					}
				}
			}
			sb.append(indexs.size() + "\n");
			for (int i : indexs) {
				sb.append((i + 1) + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
