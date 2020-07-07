import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BalancedPhoto {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO("bphoto");

		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];

		OrderStatisticTree L = new OrderStatisticTree((int) 1e5 + 1);
		OrderStatisticTree R = new OrderStatisticTree((int) 1e5 + 1);

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}

		compress(arr);

		for (int i = 0; i < N; i++)
			R.add(arr[i]);

		int count = 0;
		for (int i = 0; i < N; i++) {
			L.add(arr[i]);
			count += check(i - L.rank(arr[i]) + 1, N - i - R.rank(arr[i])) ? 1 : 0;
			R.remove(arr[i]);
		}

		out.println(count);
		out.close();
	}

	public static void compress(int[] arr) {
		int[] aux = new int[arr.length];
		System.arraycopy(arr, 0, aux, 0, arr.length);
		Arrays.sort(aux);

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++)
			map.put(aux[i], i + 1);

		for (int i = 0; i < arr.length; i++)
			arr[i] = map.get(arr[i]);
	}

	static boolean check(int L, int R) {
		return L << 1 < R || R << 1 < L;
	}

	static class OrderStatisticTree {
		int[] BIT;

		public OrderStatisticTree(int n) {
			BIT = new int[n + 1];
		}

		int getSum(int index) {
			int sum = 0;
			index++;
			while (index > 0) {
				sum += BIT[index];
				index -= index & (-index);
			}
			return sum;
		}

		int getSum(int a, int b) {
			return getSum(b) - getSum(a - 1);
		}

		void update(int index, int val) {
			index++;
			while (index < BIT.length) {
				BIT[index] += val;
				index += index & (-index);
			}
		}

		int findKthSmallest(int target) {
			int low = 0, high = BIT.length;
			int ans = -1;
			while (low <= high) {
				int mid = (low + high) >> 1;
				if (target <= getSum(mid)) {
					ans = mid;
					high = mid - 1;
				} else
					low = mid + 1;
			}
			return ans;
		}

		void add(int target) {
			update(target, 1);
		}

		void remove(int target) {
			update(target, -1);
		}

		int rank(int target) {
			return getSum(target);
		}
	}

	public static void setIO() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void setIO(String s) {
		try {
			in = new BufferedReader(new FileReader(s + ".in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter(s + ".out")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
