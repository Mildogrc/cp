import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		int l = Math.min(a.length(), b.length());
		System.out.println(a.substring(0, l).equals(b.subSequence(0, l)) ? "Yes" : "No");
	}
}
