class Medium1 {
    public int minSteps(String s, String t) {
		int[] sarr = new int[26];
		int[] tarr = new int[26];
		for(int i = 0; i<s.length(); i++) {
			sarr[charInt(s.charAt(i))]++;
			tarr[charInt(t.charAt(i))]++;
		}
		int sum = 0;
		for(int i = 0; i<sarr.length; i++) {
			sum += Math.abs(sarr[i] - tarr[i]);
		}
		return sum/2;
	}
	public static int charInt(char x) {
		int a = (int) x;
		return a-97;
	}
}
