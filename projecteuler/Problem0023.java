import java.util.ArrayList;
import java.util.List;

public class Problem0023 {
	public static void main(String[] args) {
		List<Integer> abundant = allAbundant();
		int sum = 0;
		for (int i = 1; i < 28123; i++) {
			if (!canSum(i, abundant)) {
				sum += i;
			}
		}
		System.out.println(sum);
	}

	public static int sumOfFactors(int x) {
		int sum = 1;
		int i = 2;
		for (; i * i < x; i++) {
			if (x % i == 0) {
				sum += x / i + i;
			}
		}
		if (i * i == x)
			sum += i;
		return sum;
	}

	public static List<Integer> allAbundant() {
		List<Integer> abundant = new ArrayList<>();
		for (int i = 12; i <= 28123; i++) {
			if (sumOfFactors(i) > i) {
				abundant.add(i);
			}
		}
		return abundant;
	}

	public static boolean canSum(int target, List<Integer> list) {
		int l = 0, r = list.size() - 1;
		while (l < r) {
			int sum = list.get(l) + list.get(r);
			if (sum == target) {
				return true;
			} else if (sum > target) {
				r--;
			} else {
				l++;
			}
		}
		return list.get(l) + list.get(r) == target;
	}
}

