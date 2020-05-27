class Easy {
    public int isPrefixOfWord(String sentence, String searchWord) {
		String[] tokens = sentence.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].indexOf(searchWord) == 0) {
				return i + 1;
			}
		}
		return -1;
	}
}
