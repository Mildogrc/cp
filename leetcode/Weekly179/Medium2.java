class Medium2 {
    static int maxTime = 0;

	public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
		Map<Integer, List<Integer>> child = new HashMap<>();
		int[] time = new int[n];

		for (int i = 0; i < manager.length; i++) {
			if (child.containsKey(manager[i])) {
				child.get(manager[i]).add(i);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				child.put(manager[i], list);
			}
		}
		List<Integer> bfs = new ArrayList<>();
		bfs.add(headID);
		while (bfs.size() > 0) {
			List<Integer> children = new ArrayList<>();
			itterateBFS(bfs, children, child, time, informTime);
			System.out.println(bfs.toString());
		}
		System.out.println(Arrays.toString(time));
		return max(time);
	}

	public static void itterateBFS(List<Integer> bfs, List<Integer> aux, Map<Integer, List<Integer>> map, int[] time,
			int[] inform) {
		for (int x : bfs) {
			if (!map.containsKey(x)) {
				maxTime = Math.max(time[x], maxTime);
				continue;
			}
			for (int a : map.get(x)) {
				time[a] = time[x] + inform[x];
				aux.add(a);
			}
		}
		bfs.clear();
		bfs.addAll(aux);
	}

	public static int max(int[] arr) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		return max;
	}
}
