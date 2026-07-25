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

void solve(int tc) {
    int n, m = 0;
    cin >> n >> m;
    int a[n][m];
    F0R(i, n) F0R(j, m) cin >> a[i][j];
    ifD F0R(i, n) F0R(j, m) cout << a[i][j] << " \n"[j==m-1];
    map<int, vi> x, y;
    F0R(i, n) F0R(j, m) x[a[i][j]].pb(i);
    F0R(j, m) F0R(i, n) y[a[i][j]].pb(j);
    ifD eachp(k, v, y) F0R(i, v.size()) cout << v[i] << " \n"[i==v.size()-1];
    int sum = 0;
    eachp(k, v, x) F0R(i, v.size()) sum += (i - (v.size() - i - 1)) * v[i];
    eachp(k, v, y) F0R(i, v.size()) sum += (i - (v.size() - i - 1)) * v[i];
    cout << sum << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t=1; rep(t) solve(_+1);
}