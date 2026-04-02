import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class D {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = Integer.parseInt(s.nextLine());
		Integer difference[] = new Integer[n];
		StringTokenizer st1 = new StringTokenizer(s.nextLine());
		StringTokenizer st2 = new StringTokenizer(s.nextLine());
		for (int i = 0; i < n; i++)
			difference[i] = Integer.parseInt(st1.nextToken()) - Integer.parseInt(st2.nextToken());
		Arrays.sort(difference);
		long count = 0;
		int lhs = 0, rhs = n - 1;
		while (lhs < rhs)
			if (difference[lhs] + difference[rhs] > 0)
				count += rhs-- - lhs;
			else
				lhs++;
		System.out.println(count);
	}
}
