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
    int n, k = 0;
    cin >> n;
    vi a(n); F0R(i, n) cin >> a[i];
    int min_to_check = a[0];
    F0R(i, n) min_to_check = min(min_to_check, a[i]);
    if (min_to_check != 0) {
        cout << "-1\n";
        return;
    }

    map<int, int> m, m2;
    F0R(i, n) m[a[i]]++;
    int px = -1, pc = -1, sum = 0, mx = 0;
    eachp(x, c, m) {
        if (px != -1) {
            int f = 0, diff = x - px;
            if (diff%pc != 0) f = 1;
            int t = diff/pc;
            if (t < mx) f = 1;
            if (f) { cout << "-1\n"; return; }
            m2[px] = t;
            sum += x;
            mx = t;
        }
        px = x;
        pc = c;
        // ifD cout << x << ": " << c << endl;
    }
    // ifD cout << px << " " << pc << " " << mx << endl;
    m2[px] = mx+1;
    // ifD cout << "m2: \n";
    // eachp(x, c, m2) {
    //     ifD cout << x << ": " << c << endl;
    // }
    // ifD cout << "---------------------------\n";
    F0R(i, n) cout << m2[a[i]] << " \n"[i==n-1];
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}