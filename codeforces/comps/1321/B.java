import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = toInt(br.readLine().split(" "));
		Map<Integer, Long> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			if (map.containsKey(arr[i] - i)) {
				map.put(arr[i] - i, map.get(arr[i] - i) + arr[i]);
			} else {
				map.put(arr[i] - i, (long) arr[i]);
			}
		}
		long max = 0;
		for (Map.Entry<Integer, Long> entry : map.entrySet()) {
			if (max < entry.getValue()) {
				max = entry.getValue();
			}
		}
		System.out.println(max);
	}
 
	public static int[] toInt(String[] arr) {
		int[] nums = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			nums[i] = Integer.parseInt(arr[i]);
		}
		return nums;
	}
 
	public static int sum(List<Integer> list) {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		return sum;
 
	}
 
}
