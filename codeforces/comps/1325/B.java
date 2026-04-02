import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Set<Integer> count = new HashSet<>();
			while (n-- > 0) {
				count.add(Integer.parseInt(st.nextToken()));
			}
			System.out.println(count.size());
		}
	}
}
