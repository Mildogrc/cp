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
#define int long long
// #define double long double

int comp(vi &a, int i) {
    if (i < 0 || i >= a.size()) return 0;
    return max((long long) 0, max(a[i-1], a[i+1]) + 1 - a[i]);
}

void solve(int tc) {
    int n, k = 0;
    cin >> n;
    vi a(n); F0R(i, n) cin >> a[i];
    if (n%2) {
        for(int i = 1; i < n; i+= 2) k += comp(a, i);
        cout << k << endl;
    } else {
        vi l(n), r(n);
        FOR(i, 1, n-1) (i%2) ? l[i] = comp(a, i) : r[i] = comp(a, i);
        F0R(i, n-1) l[i+1] += l[i];
        R0F(i, n-1) r[i] += r[i+1];
        ifD F0R(i, n) cout << l[i] << " \n"[i==n-1];
        ifD F0R(i, n) cout << r[i] << " \n"[i==n-1];
        int mn = min(l[n-1], r[0]);
        int i = 1, j = 4;
        while (j < n) {
            mn = min(mn, l[i] + r[j]);
            i += 2;
            j += 2;
        }
        cout << mn << endl;
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}