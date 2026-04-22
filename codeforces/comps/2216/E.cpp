#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define pb push_back
#define V vector
#define vi V<int>
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
// #define int long long
// #define double long double

void dfs(V<vi> &adj, set<pair<int, int>> &s, vi &a, int u = 0, int p = 0) {
    if(u) a[u] = (a[p] == (s.contains(mp(min(u, p), max(u, p)))));
    for (int v : adj[u]) if(v != p) dfs(adj, s, a, v, u);
}

void solve1() {
    int n, r;
    cin >> n >> r;
    r--;
    vi a(n);
    F0R(i, n-1) if(r&(1<<(i))) a[n-i-1] = 1;
    F0R(i, n-1) {
        vi e(2);
        cin >> e[0] >> e[1];
        sort(e.begin(), e.end());
        if (a[--e[0]] != a[--e[1]]) reverse(e.begin(), e.end());
        cout << ++e[0] << " " << ++e[1] << endl;
    }
}

void solve2() {
    int n;
    cin >> n;
    vi a(n);
    V<vi> adj(n);
    set<pair<int, int>> s;

    F0R(i, n-1) {
        int u, v;
        cin >> u >> v;
        s.insert(mp(--u, --v));
        adj[u].pb(v);
        adj[v].pb(u);
    }

    dfs(adj, s, a);

    int r = 0;
    F0R(i, n) r = r*2 + a[i];
    cout << ++r << endl;
}

void solve(int q) {
    if (q == 1) solve1();
    else solve2();
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t, q; cin >> t >> q; while (t--) solve(q);
}