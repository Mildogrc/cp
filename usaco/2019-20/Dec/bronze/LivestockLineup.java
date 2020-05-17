import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LivestockLineup {
	public static void main(String[] args) throws IOException {
//		Scanner s = new Scanner(System.in);
		Scanner s = new Scanner(new File("lineup.in")); // <---Change file name
		PrintWriter out = new PrintWriter(new FileWriter("lineup.out")); // <---Change file name
		int N = Integer.parseInt(s.nextLine());
		String[][] constraints = new String[N][2];
		for (int i = 0; i < N; i++) {
			constraints[i] = s.nextLine().split(" must be milked beside ");
		}
		String[] cows = { "Beatrice", "Buttercup", "Belinda", "Bessie", "Bella", "Blue", "Betsy", "Sue" };
		Arrays.sort(cows);
		int[] indices = { 1, 2, 3, 4, 5, 6, 7, 8 };

		ArrayList<ArrayList<Integer>> combos = permutation2(indices, 8);
		List<String[]> solutions = new ArrayList<>();
		for (ArrayList<Integer> combo : combos) {
			String[] cowCombo = new String[cows.length];
			for (int i = 0; i < cows.length; i++) {
				cowCombo[i] = cows[combo.get(i) - 1];
			}
			if (isSolution(constraints, cowCombo)) {
				solutions.add(cowCombo);
			}
		}
		String[] finalSolution = new String[8];
		int maxScore = 0;
//		List<String[]> maxString = new ArrayList<>();
//		for (String[] solution : solutions) {
//			int current = score(solution);
//			if (current > maxScore) {
//				maxScore = current;
//			}
//		}
//		for(int i = solutions.size()-1; i>=0; i--) {
//			if(score(solutions.get(i)) == maxScore) {
//				maxString.add(solutions.get(i));
//			}
//		}
		Collections.sort(solutions,new Comparator<String[]>() {
            public int compare(String[] strings, String[] otherStrings) {
            	for(int i = 0; i<strings.length; i++) {
            		int value = strings[i].compareTo(otherStrings[i]);
            		if(value != 0) {
            			return value;
            		}
            	}
            	return 0;
            }
        });
		finalSolution = solutions.get(0);
		for(int i = 0; i<finalSolution.length; i++) {
			out.println(finalSolution[i]);
		}
//		for (String[] solution : maxString) {
//			for (int i = 1; i <= solution.length; i++) {
//				System.out.print(solution[solution.length - i] + " ");
//			}
//			System.out.print(finalScore(solution));
//			System.out.println();
//		}
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

	public static ArrayList<ArrayList<Integer>> permutation2(int[] a, int n) {
		ArrayList<ArrayList<Integer>> gen = new ArrayList<>();
		if (n == 1) {
			ArrayList<Integer> new_permutation = new ArrayList<>();
			new_permutation.add(a[n - 1]);
			gen.add(new_permutation);
		} else {
			Iterator<ArrayList<Integer>> itr = permutation2(a, n - 1).iterator();
			while (itr.hasNext()) {
				ArrayList<Integer> permutation = itr.next();
				// (create new permutation with this element in every position)
				for (int i = 0; i <= permutation.size(); i++) {
					ArrayList<Integer> new_permutation = new ArrayList<>(permutation);
					new_permutation.add(i, a[n - 1]);
					gen.add(new_permutation);
				}
			}
		}
		return gen;
	}

	public static boolean isSolution(String[][] constraints, String[] cows) {
		for (String[] constraint : constraints) {
			int pos = pos(cows, constraint[0]);
			int pos2 = pos(cows, constraint[1]);
			if (pos + 1 != pos2 && pos - 1 != pos2) {
				return false;
			}
		}
		return true;
	}

	public static int pos(String[] cows, String cow) {
		for (int i = 0; i < cows.length; i++) {
			if (cows[i].equals(cow)) {
				return i;
			}
		}
		return -1;
	}

	static void reverse(int a[], int n) {
		int[] b = new int[n];
		int j = n;
		for (int i = 0; i < n; i++) {
			b[j - 1] = a[i];
			j = j - 1;
		}
	}

	public static int score(String[] nums) {
		String[] sorted = { "Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue" };
		int score = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				if (pos(sorted, nums[i]) < pos(sorted, nums[j])) {
					score++;
				}
			}
		}
		return score;
	}
}
