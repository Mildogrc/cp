import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class E {
    static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.divide(a.gcd(b)).multiply(b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t--> 0) {
            int n = Integer.parseInt(br.readLine());
            BigInteger[] path_lcm = new BigInteger[n];
            int[] path_count = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                String[] s = br.readLine().split(" ");
                BigInteger a = new BigInteger(s[0]);
                int k = Integer.parseInt(s[1]);

                int paths = 0;
                BigInteger l = BigInteger.ONE;
                for (int j = 0; j < k; j++) {
                    int c = Integer.parseInt(s[j+2]) - 1;
                    paths += path_count[c];
                    if (!a.gcd(path_lcm[c]).equals(BigInteger.ONE)) l = lcm(l, a.gcd(path_lcm[c]));
                }
                if (!a.gcd(l).equals(BigInteger.ONE)) {
                    path_lcm[i] = a.gcd(l);
                    path_count[i] = paths;
                } else {
                    path_lcm[i] = a;
                    path_count[i] = paths+1;
                }
                System.out.println(path_count[i]);
                System.out.flush();
            }
        }
    }
}