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

struct DSU {
    vi e;
    DSU(int n) : e(n, -1) {}
    int get(int x) { return e[x] < 0 ? x : e[x] = get(e[x]);}
    int sz(int x) { return -e[get(x)];}
    bool combine(int a, int b) {
        a = get(a);
        b = get(b);
        if (a == b) return false;
        if (e[a] > e[b]) swap(a, b);
        e[a] += e[b];
        e[b] = a;
        return true;
    }
};

void solve(int tc) {
    int n, x, y;
    cin >> n >> x >> y;
    vi a(n); F0R(i, n) cin >> a[i];
    DSU dsu(n+1);
    F0R(i, n) {
        if (i - x >= 0) dsu.combine(a[i], a[i-x]);
        if (i - y >= 0) dsu.combine(a[i], a[i-y]);
        if (i + x < n) dsu.combine(a[i], a[i+x]);
        if (i + y < n) dsu.combine(a[i], a[i+y]);
    }
    F0R(i, n) {
        if (dsu.get(i+1) != dsu.get(a[i])) {
            cout << "NO\n"; return;
        }
    }
    cout << "YES\n"; return;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}