import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class CowJog {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO("cowjog");
		String[] tokens = in.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int T = Integer.parseInt(tokens[1]);

		Place[] pos = new Place[N];

		for (int i = 0; i < N; i++) {
			tokens = in.readLine().split(" ");
			pos[i] = new Place();
			pos[i].start = Integer.parseInt(tokens[0]);
			pos[i].end = (Integer.parseInt(tokens[1]) * T) + pos[i].start;
		}

		Arrays.sort(pos, (a, b) -> Long.compare(a.start, b.start));
		for (int i = 0; i < N; i++)
			pos[i].pos = i;

		Arrays.sort(pos, (a, b) -> a.end == b.end ? b.pos - a.pos : Long.compare(a.end, b.end));

		int count = 0;
		int max = -1;
		for (int i = 0; i < N; i++) {
			
			if (max < pos[i].pos) {
				count++;
				max = pos[i].pos;
			}
		}
		out.println(count);
		out.close();
	}

	static class Place {
		long start;
		long end;
		int pos;

		@Override
		public String toString() {
			return String.valueOf(pos);
		}
	}

	public static void setIO() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void setIO(String s) {
		try {
			in = new BufferedReader(new FileReader(s + ".in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter(s + ".out")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
