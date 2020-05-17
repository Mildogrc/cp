import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Meetings {
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(new File("meetings.in")); 
		PrintWriter out = new PrintWriter(new FileWriter("meetings.out"));
		String[] tokens = s.nextLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int L = Integer.parseInt(tokens[1]);
		List<Integer> cows = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			cows.add(toInt(s.nextLine())[2]);
		}
		double threshold = ((double) N) / ((double) 2);
		int currentweight = 0;
		int collisions = 0;
		while (currentweight < threshold) {
			for (int i = 0; i < cows.size()-1; i++) {
				if (cows.get(i) > 0 && cows.get(i + 1) < 0) {
					cows.set(i, -1 * cows.get(i));
					cows.set(i + 1, -1 * cows.get(i + 1));
					i++;
					collisions++;
				}
			}
			if(cows.get(0) < 0) {
				currentweight++;
				cows.remove(0);
			}
			if(cows.get(cows.size()-1)>0) {
				currentweight++;
				cows.remove(cows.size()-1);
			}
		}
		out.println(collisions);
		out.close();
		s.close();
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
}
