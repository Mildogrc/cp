#include <bits/stdc++.h>
using namespace std;
#ifndef DEBUG
#define DEBUG 0
#endif
#define mp make_pair
#define pb push_back
#define V vector
#define vi V<int>
#define pii pair<int, int>
#define ifD if constexpr (DEBUG)
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define rep(a) F0R(_, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
#define each(a, x) for (auto &a : x)
#define eachp(u, v, x) for (auto &[u, v] : x)
#define bg(x) begin(x)
#define all(x) bg(x), end(x)
#define rall(x) rbegin(x), rend(x)
#define srt(x) sort(all(x))
#define rsrt(x) sort(rall(x))
#define ft front
#define bk back
#if DEBUG
#define dbg(x) cerr << #x << " = " << (x) << '\n'
#define dbgs(x) cerr << x << '\n'
#else
#define dbg(x)
#define dbgs(x)
#endif
template<typename T> using minheap = priority_queue<T, vector<T>, greater<T>>;
template<typename T> using maxheap = priority_queue<T>;
template<class T> auto poptop(T& x){auto v=x.top();x.pop();return v;}
template<class T> auto popq(T& x){auto v=x.front();x.pop();return v;}
constexpr int pct(int x) { return __builtin_popcount(x); }
constexpr int bits(int x) { return x == 0 ? 0 : 31 - __builtin_clz(x); }
// #define int long long
// #define double long double

void solve(int tc) {
    int n, k = 0, INF = 1e9;
    cin >> n;
    vi a(n + 1), seen(n + 1, 0); F0R(i, n) cin >> a[i+1];
    V<vi> mns(n + 2, vi(n + 2, INF)), mxs(n + 2, vi(n + 2, -INF));
    FOR(l, 1, n+1) {
        int mn = INF, mx = -INF;
        FOR(r, l, n+1) {
            int v = a[r];
            if (seen[v] == l) break;
            seen[v] = l;
            mn = min(mn, v);
            mx = max(mx, v);
            int len = r - l + 1;
            if (mx - mn + 1 == len) {
                mns[len][mn] = min(mns[len][mn], l);
                mxs[len][mn] = max(mxs[len][mn], l);
            }
        }
    }
    int ans = 0;
    for (int L = 1; 2 * L <= n; L++) {
        for (int x = 1; x + 2 * L - 1 <= n; x++) {
            int y = x + L;
            if (mns[L][x] == INF) continue;
            if (mns[L][y] == INF) continue;
            if (mns[L][x] + L - 1 < mxs[L][y] || mns[L][y] + L - 1 < mxs[L][x]) ans = max(ans, L);
        }
    }
    cout << ans << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}