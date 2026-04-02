import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FightwithMonsters {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] tokens = toInt(br.readLine());
		int a = tokens[1];
		int b = tokens[2];
		int k = tokens[3];
		int[] nums = toInt(br.readLine());
		List<Integer> desc = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			desc.add(numberOfSkips(a, b, nums[i]));
		}
		Collections.sort(desc);
		int total = 0;
		int sum = 0;
		for (int i = 0; i < desc.size(); i++) {
			sum += desc.get(i);
			if (sum > k) {
				break;
			}
			total++;
		}
		System.out.println(total);
		br.close();
	}

	private static int[] toInt(String[] tokens) {
		int[] arr = new int[tokens.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(tokens[i]);
		}
		return arr;
	}

	private static int[] toInt(String preSplit) {
		return toInt(preSplit.split(" "));
	}

	public static int numberOfSkips(int a, int b, int hp) {
		int check = hp % (a + b);
		if (check == 0) {
			return (int) Math.ceil((double) (b) / (double) a);
		}
		if (check == hp) {
			return (int) Math.ceil((double) ((hp - a) / (double) a));
		}
		if (check <= a) {
			return 0;
		}

		return (int) Math.ceil((double) ((check - a) / (double) a));
	}

}
