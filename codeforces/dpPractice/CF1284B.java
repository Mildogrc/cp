import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CF1284B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		List<MinMax> seq = new ArrayList<>();
		List<MinMax> sort = new ArrayList<>();
		long hasAcc = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sz = Integer.parseInt(st.nextToken());
			int[] sequence = new int[sz];
			MinMax mm = new MinMax();
			for (int j = 0; j < sz; j++) {
				sequence[j] = Integer.parseInt(st.nextToken());
				mm.add(sequence[j]);
			}
			if (hasAccent(sequence)) {
				hasAcc++;
			} else {
				seq.add(mm);
				sort.add(mm);
			}
		}
		sort.sort((a, b) -> b.max - a.max);
		long count = (hasAcc * (long) N) + ((long) (seq.size()) * hasAcc);
		for (MinMax mm : seq) {
			if (mm.min >= sort.get(0).max)
				continue;
			int lhs = 0, rhs = seq.size() - 1;
			while (lhs < rhs) {
				int mid = lhs + ((rhs - lhs + 1) >> 1);
				MinMax midVal = sort.get(mid);
				if (midVal.max > mm.min) {
					lhs = mid;
				} else {
					rhs = mid - 1;
				}
			}
			count += lhs + 1;
		}
		System.out.println(count);
	}

	static class MinMax {
		int min;
		int max;

		MinMax(int a, int b) {
			min = Math.min(a, b);
			max = Math.max(a, b);
		}

		MinMax(MinMax mm) {
			min = mm.min;
			max = mm.max;
		}

		MinMax() {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
		}

		void add(int x) {
			min = Math.min(min, x);
			max = Math.max(max, x);
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", min, max);
		}
	}

	public static boolean hasAccent(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] < arr[i + 1]) {
				return true;
			}
		}
		return false;
	}
}
