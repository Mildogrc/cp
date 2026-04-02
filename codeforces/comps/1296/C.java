import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			HashMap<Integer, HashMap<Integer, Integer>> dp = new HashMap<>();
			int N = Integer.parseInt(br.readLine());
			char[] directions = br.readLine().toCharArray();
			HashMap<Integer, Integer> zero = new HashMap<>();
			zero.put(0, 0);
			dp.put(0, zero);
			List<int[]> points = new ArrayList<>();
			int[] origin = new int[3];
			points.add(origin);
			int startIndex = Integer.MAX_VALUE;
			int len = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int[] current = new int[3];
				current = lastArrayCopied(points);
				addValue(current, directions[i]);
				if (dp.containsKey(current[0])) {
					if (dp.get(current[0]).containsKey(current[1])) {
						int currentLenth = current[2] - dp.get(current[0]).get(current[1]);
						if (currentLenth < len) {
							startIndex = dp.get(current[0]).get(current[1]);
							len = currentLenth;
						}
						dp.get(current[0]).replace(current[1], current[2]);
					} else {
						dp.get(current[0]).put(current[1], current[2]);
					}
				} else {
					HashMap<Integer, Integer> subMap = new HashMap<>();
					dp.put(current[0], subMap);
					dp.get(current[0]).put(current[1], current[2]);
				}
				points.add(current);
			}
			if (startIndex == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println((startIndex + 1) + " " + (startIndex + len));
			}
		}
		br.close();
	}
 
	public static int[] lastArrayCopied(List<int[]> points) {
		int[] pushArray = points.get(points.size() - 1);
		int[] returnArray = new int[pushArray.length];
		System.arraycopy(pushArray, 0, returnArray, 0, pushArray.length);
		returnArray[2]++;
		return returnArray;
	}
 
	public static void addValue(int[] arr, char a) {
		if (a == 'L') {
			arr[0]--;
		}
		if (a == 'R') {
			arr[0]++;
		}
		if (a == 'U') {
			arr[1]++;
		}
		if (a == 'D') {
			arr[1]--;
		}
	}
}
