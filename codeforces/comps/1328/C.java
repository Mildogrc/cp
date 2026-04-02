import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			char[] s = br.readLine().toCharArray();
			StringBuilder a = new StringBuilder();
			StringBuilder b = new StringBuilder();
			int i = 0;
			for (i = 0; i < n; i++) {
				if (s[i] == '2') {
					a.append('1');
					b.append('1');
					continue;
				}
				if (s[i] == '0') {
					a.append('0');
					b.append('0');
					continue;
				}
				a.append('1');
				b.append('0');
				break;
			}
			i++;
			for (; i < n; i++) {
				b.append(s[i]);
				a.append('0');
			}
			bw.write(a.toString() + "\n");
			bw.write(b.toString() + "\n");
		}
		bw.flush();
	}
}
