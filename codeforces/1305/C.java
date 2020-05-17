import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int MOD = Integer.parseInt(st.nextToken());
		int[] nums = toInt(br.readLine());
		if (n > MOD) {//Pigeonhole principle
			System.out.println(0);
		} else {
			int product = 1;
			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					product *= (Math.abs(nums[i] - nums[j])) % MOD;
					product %= MOD;
				}
			}
			System.out.println(product % MOD);
		}
 
	}
 
	static int[] toInt(String s) {
		String[] tokens = s.split(" ");
		int[] nums = new int[tokens.length];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(tokens[i]);
		}
		return nums;
	}
}
