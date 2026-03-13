#include <bits/stdc++.h>

using namespace std;

vector<int> bfs(vector<vector<int>> &adj, vector<int> &flower, vector<int> &dest, int n, int k) {
    vector<int> dist(n + 1, -1);
    vector<int> flower_count(n + 1);
    vector<vector<int>> prev(n + 1);
    queue<int> q;
    dist[1] = 0;
    q.push(1);

    while(!q.empty()) {
        int u = q.front(); q.pop();
        if (flower[u]) {
            flower_count[u]++;
        }
        for(int &v : adj[u]) {
            if(dist[v] == -1) {
                dist[v] = dist[u] + 1;
                q.push(v);
            }
            if (dist[u] + 1 == dist[v]) {
                if (flower_count[v] < flower_count[u]) {
                    prev[v].clear();
                    flower_count[v] = flower_count[u];
                }
                if (flower_count[v] == flower_count[u]) prev[v].push_back(u);
            }
        }
    }
    vector<int> good(n + 1);
    for (int &d: dest) {
        if (flower_count[d] == k) {
            q.push(d);
            good[d] = 1;
        }
    }
    while(!q.empty()){
        int u = q.front(); q.pop();
        for (int &v : prev[u]) {
            if (good[v] == 0) {
                q.push(v);
                good[v] = 1;
            }
        }
    }
    return good;
}


int main(){
    int t;
    cin >> t;
    while(t-->0) {
        int n, m, k, l;
        cin >> n >> m >> k >> l;
        vector<int> s(n + 1);
        vector<int> d(l + 1);
        for(int i = 1; i <= k; i++) {
            int field;
            cin >> field;
            s[field] = 1;
        }
        for(int i = 1; i <= l; i++) cin >> d[i];
        vector<vector<int>> adj(n + 1);
        for (int i = 0; i < m; i++) {
            int u, v;
            cin >> u >> v;
            adj[u].push_back(v);
            adj[v].push_back(u);
        }

        vector<int> good_fields = bfs(adj, s, d, n, k);
        for(int i = 2; i <= n; i++) cout << good_fields[i];
        cout << "\n";
    }
}