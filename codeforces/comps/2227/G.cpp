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
template<typename T> using minheap = priority_queue<T, vector<T>, greater<T>>;
template<typename T> using maxheap = priority_queue<T>;
#define int long long
// #define double long double

int n;
V<vi> g;
vi sub, dep;
vi anc;

void dfs(int u, int p = -1, int d = 0) {
    dep[u] = d;
    sub[u] = ((int)g[u].size() == 1) ? 1 : 0;
    for (int v : g[u]) if (v != p) {
        dfs(v, u, d + 1);
        sub[u] += sub[v];
    }
}

void dfs2(int u, int p = -1, int ae = 0) {
    anc[u] = ae;
    for (int v : g[u]) if (v != p) {
        dfs2(v, u, ae + (sub[v] % 2 == 0 ? 1 : 0));
    }
}

void solve() {
    cin >> n;
    g.assign(n + 1, {});
    sub.assign(n + 1, 0);
    dep.assign(n + 1, 0);
    anc.assign(n + 1, 0);
    F0R(i, n-1) {
        int u, v; cin >> u >> v;
        g[u].push_back(v); g[v].push_back(u);
    }
    int root = 1;
    FOR(i, 1, n+1) if (g[i].size() > 1) { root = i; break; }
    dfs(root);
    dfs2(root);

    vi leaves;
    FOR(i, 1, n+1) if ((int)g[i].size() == 1) leaves.push_back(i);
    int m = leaves.size();
    if (m % 2 == 0) {
        int ans = 0;
        FOR(u, 1, n+1) if (u != root) ans += (sub[u] % 2);
        cout << ans << "\n";
        return;
    }
    int b = 0, best = LLONG_MAX;
    FOR(u, 1, n+1) if (u != root && (sub[u] % 2 == 1)) b++;
    for (int L : leaves) {
        int cost = 2 * anc[L] - dep[L] + b;
        best = min(best, cost);
    }
    cout << best << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}