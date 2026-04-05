#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> a(n), b(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < n; i++) cin >> b[i];

    int mx = a[0];
    for (int i = 0; i < n; i++) mx = max(mx, max(a[i], b[i]));

    vector<multiset<int>> adj(mx+1);
    for (int i = 0; i < n; i++) {
        adj[a[i]].insert(b[i]);
        adj[b[i]].insert(a[i]);
    }

    int flag = 0;
    for (int i = 0; i <= mx; i++) flag |= (adj[i].size()&1);

    if (flag) {
        cout << "-1\n";
    } else {
        multiset<pair<int, int>> s;
        for (int i = 0; i<=mx; i++) {
            while (adj[i].size() > 0) {
                int u = i;
                int v = *adj[u].begin();
                adj[u].erase(adj[u].find(v));
                adj[v].erase(adj[v].find(u));
                s.insert(mp(u, v));
                i = v;
            }
        }
        // for (const auto& p : s) cout << p.first << " " << p.second << "\n";
        vector<int> v;
        for (int i = 0; i < n; i++) {
            if (s.contains(mp(a[i], b[i]))) {
                s.erase(s.find(mp(a[i], b[i])));
            } else {
                v.push_back(i+1);
                s.erase(s.find(mp(b[i], a[i])));
            }
        }
        cout << v.size() << "\n";
        for (int i = 0; i < v.size(); i++) cout << v[i] << " "; cout << "\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}