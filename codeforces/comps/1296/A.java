import java.util.Scanner;
 
public class A {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = Integer.parseInt(s.nextLine());
		for (int t = 0; t < T; t++) {
			int sum = 0;
			int N = s.nextInt();
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = s.nextInt();
				sum += arr[i];
			}
			if(sum%2==1) {
				System.out.println("YES");
			}else {
				if(allOddOrEven(arr)) {
					System.out.println("NO");
				}else {
					System.out.println("YES");
				}
			}
		}
		s.close();
	}
	public static boolean allOddOrEven(int[] arr) {
		int mod = arr[0]%2;
		for(int i = 0; i<arr.length; i++) {
			if(arr[i] %2 != mod) {
				return false;
			}
		}
		return true;
	}
}
