class Medium1 {
    public int maxVowels(String s, int k) {
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += isVowel(s.charAt(i));
		}
		int max = sum;
		for (int i = k; i < s.length(); i++) {
			sum += isVowel(s.charAt(i));
			sum -= isVowel(s.charAt(i - k));
			max = Math.max(max, sum);
		}
		return max;
	}

	public static int isVowel(char x) {
		return (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') ? 1 : 0;
	}
}
