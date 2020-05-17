import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			if(n==1) {
				log.write("-1\n");
				continue;
			}
			for (int i = 0; i < n - 1; i++) {
				log.write("5");
			}
			log.write("4\n");
		}
		log.flush();
	}
}
