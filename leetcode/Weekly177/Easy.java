import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Easy {
    public int daysBetweenDates(String date1, String date2) {
		int[] dateone = toInt(date1.split("-"));
		int[] dateTwo = toInt(date2.split("-"));
		LocalDate d1 = LocalDate.of(dateone[0], dateone[1], dateone[2]);
		LocalDate d2 = LocalDate.of(dateTwo[0], dateTwo[1], dateTwo[2]);
		return Math.abs((int) ChronoUnit.DAYS.between(d1, d2));
	}

	private static int[] toInt(String[] tokens) {
		int[] arr = new int[tokens.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(tokens[i]);
		}
		return arr;
	}
}
