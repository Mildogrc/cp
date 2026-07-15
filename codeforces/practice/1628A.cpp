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
#define pv(a) ifD {each(x, a) cout << x << " "; cout << endl;}
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
    int n, k = 0;
    cin >> n;
    vi a(n); F0R(i, n) cin >> a[i];

    stack<int> st;
    R0F(i, n) if (st.empty() || a[i] >= st.top()) st.push(a[i]);

    vi c(n+1), ans;
    int mex = n;
    F0R(i, n) c[a[i]]++;
    R0F(i, n+1) if(!c[i]) mex = min(mex, i);
    ifD cout << "first mex: " << mex << endl;
    int i = 0;
    while(i < n) {
        if (mex == 0) {
            while (i++ < n) ans.pb(0);
            break;
        }
        int newmex = mex;
        set<int> mex_check;
        while(mex_check.size() < mex) {
            if (a[i] < mex) mex_check.insert(a[i]);
            if (--c[a[i]] == 0) newmex = min(newmex, a[i]);
            i++;
        }
        ans.pb(mex);
        mex = newmex;
    }
    cout << ans.size() << endl;
    each(x, ans) cout << x << " "; cout << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}