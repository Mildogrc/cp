import java.util.Arrays;
import java.util.Stack;

public class RollbackDSU {
	int[] e;

	boolean rollback, cycfind;
	Stack<int[][]> history;

	int find(int x) {
		if (e[x] < 0) {
			return x;
		} else {
			return e[x] = find(e[x]);
		}
	}

	boolean sameSet(int a, int b) {
		return find(a) == find(b);
	}

	int size(int x) {
		return -e[find(x)];
	}

	RollbackDSU() {
		this(0);
	}

	RollbackDSU(int N) {
		e = new int[N];
		Arrays.fill(e, -1);
		history = new Stack<>();
	}

	boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			if (rollback)
				history.add(null);
			cycfind = true;
			return false;
		}
		if (e[x] > e[y]) {
			int t = x;
			x = y;
			y = t;
		}
		if (rollback)
			history.add(new int[][] { new int[] { x, y }, new int[] { e[x], e[y] } });
		e[x] += e[y];
		e[y] = x;
		return true;
	}

	void setparent(int a, int b) {
		e[b] = -1;
		if (e[a] < 0)
			e[a] = b;
		else
			e[e[a]] = b;
	}

	void setrb() {
		rollback = true;
	}

	void rollback() {
		int a[][] = history.pop();
		if (a != null) {
			e[a[0][0]] = a[1][0];
			e[a[0][1]] = a[1][1];
		}
	}

	int compcount() {
		int ret = 1;
		for (int i = 0; i < e.length; i++)
			if (find(i) != find(i + 1))
				ret++;
		return ret;
	}

	boolean cyc() {
		return cycfind;
	}
}
