import java.util.ArrayList;
import java.util.List;

public class Problem0024 {
	static List<String> perms;

	public static void main(String[] args) {
		perms = new ArrayList<>();
		int arr[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		heapPermutation(arr, arr.length, arr.length);// very very slow
		perms.sort((a, b) -> a.compareTo(b));
		System.out.println(perms.get((int) 1e6 - 1));
	}

	static void heapPermutation(int a[], int size, int n) {
		if (size == 1) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < a.length; i++)
				sb.append(a[i]);
			perms.add(sb.toString());
		}
		for (int i = 0; i < size; i++) {
			heapPermutation(a, size - 1, n);
			if (size % 2 == 1) {
				int temp = a[0];
				a[0] = a[size - 1];
				a[size - 1] = temp;
			} else {
				int temp = a[i];
				a[i] = a[size - 1];
				a[size - 1] = temp;
			}
		}
	}
}
