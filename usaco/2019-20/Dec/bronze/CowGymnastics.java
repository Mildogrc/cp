import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CowGymnastics {
	public static void main(String[] args) throws IOException {
//		Scanner s = new Scanner(System.in);
		Scanner s = new Scanner(new File("gymnastics.in")); // <---Change file name
		PrintWriter out = new PrintWriter(new FileWriter("gymnastics.out")); // <---Change file name
		int[] tokens = toInt(s.nextLine());
		int K = tokens[0];
		int N = tokens[1];
		int[][] rankings = new int[K][N];
		for (int i = 0; i < K; i++) {
			rankings[i] = toInt(s.nextLine());
		}
		List<int[]> eachRank = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			int[] rank = new int[K];
			for (int j = 0; j < rankings.length; j++) {
				rank[j] = index(rankings[j], i);
			}
			eachRank.add(rank);
		}
		int pairs = 0;
		for (int i = 0; i < eachRank.size(); i++) {
			for (int j = 0; j < eachRank.size(); j++) {
				if (i != j) {
					if (greater(eachRank.get(i), eachRank.get(j))) {
						pairs++;
					}
				}
			}
		}
		out.println(pairs);
		out.close();
		s.close();
	}

	public static int[] toInt(String s) {
		String[] tokens = s.split(" ");
		int[] intArray = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			intArray[i] = Integer.parseInt(tokens[i]);
		}
		return intArray;
	}

	public static int index(int arr[], int t) {
		if (arr == null) {
			return -1;
		}
		int len = arr.length;
		int i = 0;
		while (i < len) {

			if (arr[i] == t) {
				return i;
			} else {
				i = i + 1;
			}
		}
		return -1;
	}

	public static boolean greater(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] < b[i]) {
				return false;
			}
		}
		return true;
	}
}
