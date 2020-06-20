import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.TreeSet;

public class Stampede {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO("stampede");
		int N = Integer.parseInt(in.readLine());

		Seg[] segs = new Seg[N];
		TreeSet<Double> stops = new TreeSet<>();

		for (int i = 0; i < N; i++) {
			String[] tokens = in.readLine().split(" ");
			long x = -Integer.parseInt(tokens[0]) - 1;
			long h = Integer.parseInt(tokens[1]);
			long s = Integer.parseInt(tokens[2]);

			long l = x * s;
			long r = l + s;

			stops.add((double) l - 0.5);
			stops.add((double) r - 0.5);
			stops.add((double) l + 0.5);
			stops.add((double) r + 0.5);

			segs[i] = new Seg(l, r, h);
		}

		boolean[] seen = new boolean[N];
		for (double m : stops) {
			long min = Long.MAX_VALUE;
			int vis = -1;
			for (int i = 0; i < N; i++) {
				if (segs[i].l <= m && m <= segs[i].r) {
					if (segs[i].h < min) {
						min = segs[i].h;
						vis = i;
					}
				}
			}
			if (vis >= 0)
				seen[vis] = true;
		}
		int count = 0;
		for (boolean b : seen)
			if (b)
				count++;
		out.println(count);
		out.close();
	}

	static class Seg {
		long l, r, h;

		Seg(long left, long right, long height) {
			l = left;
			r = right;
			h = height;
		}

		long len() {
			return r - l;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", l, r);
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
