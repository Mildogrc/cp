import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MooBuzz {
	public static void main(String[] args) throws IOException {
//		Scanner s = new Scanner(System.in);
		Scanner s = new Scanner(new File("moobuzz.in"));
		PrintWriter out = new PrintWriter(new FileWriter("moobuzz.out"));
		int N = Integer.parseInt(s.nextLine());
		int val = 0;
		for (int i = 1;; i += 100) {
			int three = countDivisibles(1, i, 3);
			int five = countDivisibles(1, i, 5);
			int fifteen = countDivisibles(1, i, 15);
			int count = three + five - fifteen;
			if ((i - count) >= N) {
				val = i;
				break;
			}
		}
		val -= 100;
		int answer = 0;
		for (int i = val;; i++) {
			int three = countDivisibles(1, i, 3);
			int five = countDivisibles(1, i, 5);
			int fifteen = countDivisibles(1, i, 15);
			int count = three + five - fifteen;
			if ((i - count) == N) {
				answer = i;
				break;
			}
		}
		out.println(answer);
		s.close();
		out.close();
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

	static int countDivisibles(int A, int B, int K) {
		int firstDivisible = A % K == 0 ? A : A + (K - A % K);
		int lastDivisible = B % K == 0 ? B : B - B % K; // B/K behaves this way by default.
		return (lastDivisible - firstDivisible) / K + 1;
	}
//	public static int max(int N) {
//		int nums = 0;
//		int answer = 0;
//		for (int i = 1; nums < N; i++) {
//			if (!(i % 3 == 0 || i % 5 == 0)) {
//				nums++;
//			}
//			answer = i;
//		}
//		return answer;
//	}

}
