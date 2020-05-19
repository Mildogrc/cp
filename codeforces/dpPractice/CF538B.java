import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CF528B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] num = br.readLine().toCharArray();
		int N = num.length;
		int max = 0;
		for (int i = 0; i < num.length; i++) {
			max = Math.max(max, num[i] - '0');
		}
		int[] sequence = new int[max];
		for (int i = 0; i < num.length; i++) {
			int count = num[i] - '0';
			int add = pow(10, N - i - 1);
			for (int c = 0; c < count; c++) {
				sequence[c] += add;
			}
		}
		System.out.println(max);
		for (int i = 0; i < max; i++) {
			System.out.print(sequence[i] + " ");
		}
	}

	public static int pow(int b, int p) {// Binary Exponentiation
		if (p == 0)
			return 1;
		int prod = 1;
		int num = b;
		while (p > 1) {
			if (p >> 1 << 1 != p) {
				prod *= num;
			}
			p >>= 1;
			num *= num;
		}
		prod *= num;
		return prod;
	}
}
