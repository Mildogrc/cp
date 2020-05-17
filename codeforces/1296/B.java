import java.util.Scanner;
 
public class B {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = Integer.parseInt(s.nextLine());
		for (int t = 0; t < T; t++) {
			int x = Integer.parseInt(s.nextLine());
			int total = x;
			while (x > 0) {
				if (x < 10) {
					x = 0;
					total += x;
				} else {
					total += (x - 10) / 9;
					x -= total;
				}
			}
			if(total>=10) {
				total++;
			}
			System.out.println(total);
		}
		s.close();
	}
}
