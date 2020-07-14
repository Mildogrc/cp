import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
public class task1666 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);
 
		UnionFind uf = new UnionFind(N);
		for (int i = 0; i < M; i++) {
			tokens = br.readLine().split(" ");
			uf.union(Integer.parseInt(tokens[0]) - 1, Integer.parseInt(tokens[1]) - 1);
		}
 
		Set<Integer> set = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++)
			if (set.add(uf.find(i)))
				list.add(uf.find(i) + 1);
		System.out.println(set.size() - 1);
		for (int i = 1; i < list.size(); i++)
			System.out.println(list.get(i - 1) + " " + list.get(i));
	}
 
	static class UnionFind {
		int[] parent;
 
		public UnionFind(int N) {
			parent = new int[N];
			Arrays.fill(parent, -1);
		}
 
		int size() {
			return parent.length;
		}
 
		int getComponentSize(int x) {
			return -parent[find(x)];
		}
 
		int find(int x) {
			assert (x >= 0 && x < size());
			int y = x;
			while (parent[y] >= 0)// find overall Leader
				y = parent[y];
			while (parent[x] >= 0) {// Path compression
				int nxt = parent[x];
				parent[x] = y;
				x = nxt;
			}
			return y;
		}
 
		boolean union(int x, int y) {
			x = find(x);
			y = find(y);
			if (parent[x] > parent[y]) {
				int t = x;
				x = y;
				y = t;
			}
			if (x != y) {
				parent[x] += parent[y];// rank
				parent[y] = x;// lazy
				return true;
			}
			return false;
		}
 
		boolean sameSet(int x, int y) {
			return find(x) == find(y);
		}
	}
}
