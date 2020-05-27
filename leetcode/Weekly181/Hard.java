class Hard {
	public String longestPrefix(String s) {
		int x = longestPrefixSuffix(s);
		return s.substring(0, x);
	}

	static int longestPrefixSuffix(String s) {
		int sizeOfS = s.length();
		int substrings[] = new int[sizeOfS];
		substrings[0] = 0;
		int len = 0;
		int i = 1;
		while (i < sizeOfS) {
			if (s.charAt(i) == s.charAt(len)) {
				len++;
				substrings[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = substrings[len - 1];
				} else {
					substrings[i] = 0;
					i++;
				}
			}
		}
		int res = substrings[sizeOfS - 1];
		return res;
	}
}
