import java.time.DayOfWeek;
import java.time.LocalDate;

public class Problem0019 {
	public static void main(String[] args) {
		int count = 0;
		for (int i = 1901; i <= 2000; i++) {
			for (int j = 1; j <= 12; j++) {
				if (DayOfWeek.from(LocalDate.of(i, j, 1)).name().equals("SUNDAY")) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
}

