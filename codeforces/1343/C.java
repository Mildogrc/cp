import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[n];
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[i]);
			}
			if (max < 0) {
				System.out.println(max);
				continue;
			}
			long sum = 0;
			List<List<Integer>> vals = new ArrayList<>();
			List<Integer> firstList = new ArrayList<>();
			firstList.add(arr[0]);
			vals.add(firstList);
			for (int i = 1; i < n; i++) {
				if (arr[i] > 0 && arr[i - 1] < 0 || arr[i] < 0 && arr[i - 1] > 0) {
					List<Integer> list = new ArrayList<>();
					list.add(arr[i]);
					vals.add(list);
				} else {
					vals.get(vals.size() - 1).add(arr[i]);
				}
			}
			for (List<Integer> x : vals) {
				int max1 = x.get(0);
				for (int i = 1; i < x.size(); i++) {
					max1 = Math.max(max1, x.get(i));
				}
				sum += max1;
			}
			System.out.println(sum);
		}
	}
}
