#include <bits/stdc++.h>
using namespace std;

int bfs(vector<vector<int>> &adj, vector<int> &dist, vector<int> &visited, int s, int n) {
    queue<int> q;
    q.push(s);
    dist[s] = 0;
    int count= 1;
    while(!q.empty()) {
        int u = q.front(); q.pop();
        count++;
        for (int v : adj[u]) {
            if (dist[v] == -1) {
                q.push(v);
                dist[v] = 1+dist[u];
            } 
        }
    }

    q.push(s);
    vector<int> both(2);
    visited[s] = 1;
    while(!q.empty()) {
        int u = q.front(); q.pop();
        both[dist[u]%2]++;
        for (int v : adj[u]) {
            if (dist[v]%2 == dist[u]%2) {
                return 0;
            } 
            if (visited[v] == 0) q.push(v);
            visited[v] = 1;
        }
    }
    return max(both[0], both[1]);
}

void solve() {
    int n, m;
    cin >> n >> m;
    vector<vector<int>> adj(n+1);
    for(int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    vector<int> dist(n+1, -1);
    vector<int> visited(n+1);
    int sum = 0;
    for(int i = 1; i <= n; i++ ) {
        if(dist[i] == -1) {
            sum += bfs(adj, dist, visited, i, n);
        }
    }
    // cout << "ans:";
    cout << sum << "\n";
    // cout << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}