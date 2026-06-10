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

vi exp(int x) {
    vi ans;
    ans.pb(x);
    while (x != 1) {
        if (x&1) x+=1;
        else x/=2;
        ans.pb(x);
    }
    reverse(ans.bg(), ans.end());
    return ans;
}

int good(V<vi> &ans, int x, int n) {
    F0R(i, n) if (ans[i].size() <= x || ans[i][x] != ans[0][x]) return 0;
    return 1;
}

int sub_solve(V<vi> ans, int n) {
    int lo = 0, hi = 100000, k = 0;;
    while (lo < hi) {
        int mid = (lo + hi + 1)/2;
        if (good(ans, mid, n)) lo = mid;
        else hi = mid - 1;
    }
    F0R(i, n) k += ans[i].size() - lo - 1;
    return k;
}

void solve(int tc) {
    int n, k = 0;
    cin >> n;
    vi a(n); F0R(i, n) cin >> a[i];
    V<vi> ans(n);
    F0R (i, n) {
        ans[i] = exp(a[i]);
        ifD each(j, ans[i]) cout << j << " "; 
        ifD cout << endl;
    }
    k = sub_solve(ans, n);
    F0R (i, n) {
        if (ans[i].size() == 1) {
            ans[i].pb(2); ans[i].pb(1);
        }
    }

    k = min(k, sub_solve(ans, n));
    cout << k << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}