#include <bits/stdc++.h>
using namespace std;
#ifndef DEBUG
#define DEBUG 0
#endif
#define mp make_pair
#define pb push_back
#define V vector
#define vi V<int>
#define ifD if constexpr (DEBUG)
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define rep(a) F0R(_, a)
#define ROF(i, a, b) for (long long i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
#define each(a, x) for (auto &a : x)
#define bg(x) begin(x)
#define all(x) bg(x), end(x)
#define rall(x) rbegin(x), rend(x)
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
template<class T> auto popft(T& x){auto v=x.front();x.pop_front();return v;}
template<class T> auto popbk(T& x){auto v=x.back();x.pop_back();return v;}
constexpr int pct(int x) { return __builtin_popcount(x); }
constexpr int bits(int x) { return x == 0 ? 0 : 31 - __builtin_clz(x); }
#define ll long long
// #define double long double

ll diff(ll a, ll b, ll c, ll d) {
    return abs(((a|b) - (a&c)) - d);
}

void solve(int tc, vector<ll> &p) {
    vector<ll> a(4), ba(4);
    cin >> a[1] >> a[2] >> a[3];

    R0F(i, 62) {
        F0R(j, 4) ba[j] = !!(a[j] & p[i]);
        ifD F0R(j, 4) cout << ba[j] << " \n"[j==3];
        if(1 - (1&ba[2]) == ba[3]) a[0] |= p[i];
    }
    if(diff(a[0], a[1], a[2], a[3])) cout << "-1" << endl;
    else cout << a[0] << endl;
    ifD cout << a[0] << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    vector<ll> p(62); p[0] = 1L; FOR(i, 1, 62) p[i] = p[i-1]*2L;
    int t; cin >> t; rep(t) solve(_+1, p);
}