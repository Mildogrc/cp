class Medium1 {
    public int[] processQueries(int[] queries, int m) {
		int[] result = new int[queries.length];
		List<Integer> arr = new ArrayList<>();
		for (int i = 1; i <= m; i++) {
			arr.add(i);
		}
		for (int i = 0; i < queries.length; i++) {
			result[i] = arr.indexOf(queries[i]);
			arr.remove(result[i]);
			arr.add(0, queries[i]);
		}
		return result;
	}
}
