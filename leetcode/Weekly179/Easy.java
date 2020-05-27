class Easy {
    public String generateTheString(int n) {
		char[] x = new char[n];
		Arrays.fill(x, 'a');
		if (n % 2 == 0) {
			x[0] = 'b';
		}
		return String.valueOf(x);
	}
}
