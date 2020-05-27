class Easy {
	public String destCity(List<List<String>> paths) {
		Set<String> set = new HashSet<>();
		for (var list : paths) {
			for (String cities : list) {
				set.add(cities);
			}
		}
		for (var list : paths) {
			set.remove(list.get(0));
		}
		for (var x : set) {
			return x;
		}
		return "";
	}
}
