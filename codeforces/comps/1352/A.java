import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class A {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			List<Integer> nums = new ArrayList<>();
			while (N > 0) {
				nums.add(N % 10);
				N /= 10;
			}
			int count = 0;
			for (int i = 0; i < nums.size(); i++) {
				if (nums.get(i) != 0)
					count++;
			}
			System.out.println(count);
			for (int i = 0; i < nums.size(); i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(nums.get(i));
				for (int j = 0; j < i; j++) {
					sb.append('0');
				}
				if (Integer.parseInt(sb.toString()) != 0) {
					System.out.print(sb.toString() + " ");
				}
			}
			System.out.println();
		}
	}
}
