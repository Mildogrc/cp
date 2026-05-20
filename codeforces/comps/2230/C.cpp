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
    int n;
    cin >> n;
    vi a1(n); F0R(i, n) cin >> a1[i];
    if (n == 1 && a1[0] == 2) {
        cout << "0\n"; return;
    } 
    int c1 = 0; F0R(i, n) if(a1[i] == 1) c1++;
    vi a; F0R(i, n) if(a1[i] != 1) a.pb(a1[i]);
    n = a.size();
    ifD F0R(i, n) cout << a[i] << " \n"[i==n-1];
    int sum = 0; F0R(i, n) sum += a[i];
    int onepos = 0;
    if (n == 1) onepos = a[0]/2;
    else F0R(i, n) onepos += (a[i]/2)-1;
    ifD cout << onepos << endl;
    ifD cout << "finalans: ";
    cout << sum + min(onepos, c1) << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}