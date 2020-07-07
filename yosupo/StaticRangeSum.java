import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class StaticRangeSum {
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		setIO();
		String[] tokens = in.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int Q = Integer.parseInt(tokens[1]);

		Long[] arr = new Long[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		BIT<Long> bit = new BIT<>(N, (a, b) -> a + b);

		for (int i = 0; i < N; i++) {
			bit.update(i, Long.parseLong(st.nextToken()));
		}

		for (int q = 0; q < Q; q++) {
			tokens = in.readLine().split(" ");
			Long l = bit.get(Integer.parseInt(tokens[0]) - 1);
			Long r = bit.get(Integer.parseInt(tokens[1]) - 1);
			if (l == null)
				out.println(r);
			else
				out.println(r - l);
		}
		out.close();
	}

	static class BIT<T> {
		public static interface Combiner<T> {
			T combine(T l, T r);
		}

		T[] BIT;
		T[] input;
		int N;
		Combiner<T> c;

		BIT(int N, Combiner<T> c) {
			this((T[]) new Object[N], c);
		}

		BIT(T[] arr, Combiner<T> c) {
			this.c = c;
			N = arr.length;
			input = arr;
			createTree(arr);
		}

		public void update(int i, T val) {
			i++;
			while (i <= N) {
				BIT[i] = combine(BIT[i], val);
				i += i & -i;
			}
		}

		void createTree(T[] arr) {
			BIT = (T[]) new Object[N + 1];
			for (int i = 0; i < N; i++)
				update(i, arr[i]);
		}

		T get(int i) {
			T sum = null;
			i++;
			while (i > 0) {
				sum = combine(BIT[i], sum);
				i -= i & -i;
			}
			return sum;
		}

		public T combine(T a, T b) {
			if (a == null && b == null) {
				return null;
			}
			if (a == null) {
				return b;
			}
			if (b == null) {
				return a;
			}
			return c.combine(a, b);
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
