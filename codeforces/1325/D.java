import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
 
public class D {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long u = Long.parseLong(st.nextToken());
		long v = Long.parseLong(st.nextToken());
		if ((u > v) || (u >> 1 << 1 == u ^ v >> 1 << 1 == v)) {
			System.out.println(-1);
		} else if (u == 0 && v == 0) {
			System.out.println(0);
		} else if (u == v) {
			System.out.println("1\n" + u);
		} else {
			long x = (v + u) >> 1;
			long y = v - x;
			BigInteger x1 = BigInteger.valueOf(x);
			BigInteger y1 = BigInteger.valueOf(y);
			BigInteger u1 = BigInteger.valueOf(u);
			if (x + y == v && (x1.xor(y1).equals(u1)))
				System.out.printf("2\n%d %d", x, y);
			else
				System.out.printf("3\n%d %d %d", u, (v - u) >> 1, (v - u) >> 1);
		}
	}
}
