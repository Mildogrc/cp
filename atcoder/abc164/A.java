import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class A {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		System.out.println(Integer.parseInt(tokens[0]) > Integer.parseInt(tokens[1]) ? "safe" : "unsafe");
	}
}
