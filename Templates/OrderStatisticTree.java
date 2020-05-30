public class OrderStatisticTree {
	int[] BIT;

	public OrderStatisticTree(int sz) {
		BIT = new int[sz];
	}

	void update(int i, int add) {
		while (i > 0 && i < BIT.length) {
			BIT[i] += add;
			i = i + (i & (-i));
		}
	}

	int sum(int i) {
		int ans = 0;
		while (i > 0) {
			ans += BIT[i];
			i = i - (i & (-i));
		}

		return ans;
	}

	int getKth(int k) {
		int l = 0;
		int h = BIT.length;
		while (l < h) {
			int mid = (l + h) / 2;
			if (k <= sum(mid))
				h = mid;
			else
				l = mid + 1;
		}

		return l;
	}

	void add(int x) {
		update(x, 1);
	}

	void remove(int x) {
		update(x, -1);
	}

	int rank(int x) {
		return sum(x);
	}
}
