import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class C {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
 
		int[] arr = new int[N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		long GCD = 1;
		int prime = 0;
		List<Integer> allPrimes = new ArrayList<>();
		while (prime < max) {
			prime = nextPrime(prime);
			allPrimes.add(prime);
		}
		for (int p : allPrimes) {
			int min1 = Integer.MAX_VALUE;
			int min2 = Integer.MAX_VALUE;
			for (int i = 0; i < N && min2 > 0; ++i) {
				int count = 0;
				int P = arr[i];
				while (P % p == 0) {
					++count;
					P /= p;
				}
				if (count < min1) {
					min2 = min1;
					min1 = count;
				} else if (count < min2) {
					min2 = count;
				}
			}
			for (int k = 0; k < min2; ++k) {
				GCD *= p;
			}
		}
		System.out.println(GCD);
 
	}
 
	static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;
 
		for (int i = 5; i * i <= n; i = i + 6)
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
 
		return true;
	}
 
	static int nextPrime(int N) {
		if (N <= 1)
			return 2;
 
		int prime = N;
		boolean found = false;
		while (!found) {
			prime++;
 
			if (isPrime(prime))
				found = true;
		}
		return prime;
	}
 
}
