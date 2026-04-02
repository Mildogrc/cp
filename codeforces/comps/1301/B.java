import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int nums[] = toInt(br.readLine());
			if(allnegativeones(nums)) {
				System.out.println(0 + " " + 0);
				continue;
			}
			if (N == 2) {
				if(nums[0] == -1 && nums[1] == -1) {
					System.out.printf("%d %d\n", 0, 0);
				}else {
					int a = 0;
					if(nums[0] != -1) {
						a = nums[0];
					}else {
						a = nums[1];
					}
					System.out.printf("%d %d\n", 0, a);
				}
				continue;
			}
			int k = average(nums);
			int maxSize = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == -1) {
					nums[i] = k;
				}
			}
			for (int i = 0; i < nums.length - 1; i++) {
				maxSize = Math.max(maxSize, Math.abs(nums[i + 1] - nums[i]));
			}
			System.out.printf("%d %d\n", maxSize, k);
		}
	}
 
	public static int average(int[] arr) {
		List<Integer> list = new ArrayList<>();
		list.add(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (list.get(list.size() - 1) != -1) {
				list.add(arr[i]);
			} else {
				if (arr[i] != -1) {
					list.add(arr[i]);
				}
			}
		}
		List<Integer> next = new ArrayList<>();
		int i = 0;
		if (list.get(i) == -1) {
			next.add(list.get(i + 1));
		}
		for (i = 1; i < list.size() - 1; i++) {
			if (list.get(i) == -1) {
				next.add(list.get(i + 1));
				next.add(list.get(i - 1));
			}
		}
		if (list.get(i) == -1) {
			next.add(list.get(i - 1));
		}
		Collections.sort(next);
 
		return (next.get(0) + next.get(next.size() - 1) + 1) / 2;
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
	public static boolean allnegativeones(int[] arr) {
		for(int i = 0; i<arr.length; i++) {
			if(arr[i] != -1) {
				return false;
			}
		}
		return true;
	}
}
