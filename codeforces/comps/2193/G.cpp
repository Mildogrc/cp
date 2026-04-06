#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void dfs(vector<vector<int>> &adj, int u, int p, queue<int> &q) {
    q.push(u);
    for (const auto &v: adj[u]) if (v != p) dfs (adj, v, u, q);
}

void solve() {
    int n;
    cin >> n;
    vector<vector<int>> adj(n+1);
    for (int i = 1; i < n; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    queue<int> q;
    dfs(adj, 1, 1, q);
    
    while (q.size() > 1) {
        int u = q.front(); q.pop();
        int v = q.front(); q.pop();
        cout << "? " << u << " " << v << endl;
        int response;
        cin >> response;
        if (response) {
            cout << "? " << u << " " << u << endl;
            cin >> response;
            int ans;
            if (response) ans = u;
            else ans = v;
            cout << "! " << ans << endl;
            return;
        }
    }
    cout << "! " << q.front() << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}