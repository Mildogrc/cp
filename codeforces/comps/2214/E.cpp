#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

struct Edge {
    int v, w;
    Edge(int x, int y) : v(x), w(y) {}
    bool operator()(const Edge& a, const Edge& b) const {
        return a.w > b.w;
    }
};



void solve() {
    int n, m;
    cin >> n >> m;
    vector<vector<Edge>> adj(n+1, vector<int>);
    priority_queue<Edge, vector<Edge>, Compare> pq;
    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back(Edge(v, w));
    }
    vector<int> dist(n+1, INT_MAX);
    // vecotr<int> visited(n+1, 0);
    dist[1] = 0;
    queue<int> q;
    q.push(1);
    while (!q.empty) {
        int u = q.front();q.pop();
        for (const Edge& e : adj[u]) {
            dist[e.v] = min(dist[e.v], dist[u] + e.w);
            
        }
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}