#include <bits/stdc++.h>
using namespace std;

int dfs(vector<vector<int>> &adj, int v, int p, int k) {
    priority_queue<int> pq;
    for(int x: adj[v]) {
        if (x != p) {
            int d = dfs(adj, x, v, k);
            pq.push(d);
            if (pq.size() == 3) pq.pop();
        }
    }
    if (pq.size() == 0) return 0;
    if (pq.size() == 1) return 1 + pq.top();
    int s2 = pq.top();
    pq.pop();
    int s1 = pq.top();
    if (s1 + s2 + 1 <= k) return 0;
    return s1 + 1;
}

int main() {
    int t;
    cin >> t;
    while (t-->0) {
        int n, k, v;
        cin >> n >> k >> v;
        vector<vector<int>> adj;
        for(int i = 0; i <= n; i++) {
            vector<int> row;
            adj.push_back(row);
        }

        for (int i = 0; i < n - 1; i++) {
            int u, v;
            cin >> u >> v;
            // cout << "uv pair: " << u << " " << v << endl;
            adj[u].push_back(v);
            adj[v].push_back(u);
        }
        if (dfs(adj, v, v, k) == 0) {
            cout << "YES";
        } else {
            cout << "NO";
        }
        cout << "\n";
    }
}