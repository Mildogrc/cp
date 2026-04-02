import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long max = 0;
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(st.nextToken());
			bw.write((max + curr) + " ");
			if (curr > 0)
				max += curr;
		}
		bw.flush();
	}
}
