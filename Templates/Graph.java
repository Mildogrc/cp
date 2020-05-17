import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Graph {
	int V;
	List<Integer>[] adj;

	Graph(int V) {
		this.V = V;
		adj = new List[V];
		for (int i = 0; i < V; i++) {
			List<Integer> temp = new ArrayList<>();
			adj[i] = temp;
		}
	}

	void addEdge(int src, int dest) {
		this.adj[src].add(dest);
		this.adj[dest].add(src);
	}

	void BFS(Queue<Integer> queue, boolean[] visited, int[] parent) {
		int current = queue.poll();
		for (int j = 0; j != adj[current].size(); j++) {
			int i = adj[current].get(j);
			if (!visited[i]) {
				parent[i] = current;

				visited[i] = true;

				queue.add(i);
			}
		}
	}

	int isIntersecting(boolean[] s_visited, boolean[] t_visited) {
		for (int i = 0; i < V; i++) {
			if (s_visited[i] && t_visited[i])
				return i;
		}
		return -1;
	}

	int[] printPath(int[] s_parent, int[] t_parent, int s, int t, int intersectNode) {
		List<Integer> path = new ArrayList<>();
		path.add(intersectNode);
		int i = intersectNode;
		while (i != s) {
			path.add(s_parent[i]);
			i = s_parent[i];
		}
		Collections.reverse(path);
		i = intersectNode;
		while (i != t) {
			path.add(t_parent[i]);
			i = t_parent[i];
		}
		int[] arr = new int[path.size()];
		for (int j = 0; j < arr.length; j++) {
			arr[j] = path.get(j);
		}
		return arr;
	}

	int[] shortestPath(int s, int t) {
		boolean[] s_visited = new boolean[V];
		boolean[] t_visited = new boolean[V];

		int[] s_parent = new int[V];
		int[] t_parent = new int[V];

		Queue<Integer> s_queue = new ArrayDeque<>();
		Queue<Integer> t_queue = new ArrayDeque<>();

		int intersectNode = -1;

		for (int i = 0; i < V; i++) {
			s_visited[i] = false;
			t_visited[i] = false;
		}

		s_queue.add(s);
		s_visited[s] = true;

		s_parent[s] = -1;

		t_queue.add(t);
		t_visited[t] = true;

		t_parent[t] = -1;

		while (s_queue.size() != 0 && t_queue.size() != 0) {
			BFS(s_queue, s_visited, s_parent);
			BFS(t_queue, t_visited, t_parent);
			intersectNode = isIntersecting(s_visited, t_visited);
			if (intersectNode != -1) {
				return printPath(s_parent, t_parent, s, t, intersectNode);
			}
		}
		System.out.println("-1");
		System.exit(0);
		return null;
	}

}
