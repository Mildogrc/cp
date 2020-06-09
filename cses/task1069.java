import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class task1069 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		int max = 0;
		int curr = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1]) {
				curr++;
			} else {
				max = Math.max(max, curr);
				curr = 0;
			}
		}
		max = Math.max(curr, max);
		System.out.println(max + 1);
	}
}
