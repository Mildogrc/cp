import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int po = Integer.parseInt(st.nextToken());
		int z = Integer.parseInt(st.nextToken());
		int no = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if (K <= po) {
			System.out.println(K);
		} else if (K <= po + z) {
			System.out.println(po);
		} else {
			System.out.println(po - (K - po - z));
		}
	}
}
