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

string build_lower(string a, set<int>& d, int mxint) {
    int an = a.length();
    string best = "";
    F0R(pos, an) {
        string s = "";
        bool ok = true;
        F0R(i, pos) {
            int cur = a[i] - '0';
            if (!d.count(cur)) ok = false;
            s.pb(a[i]);
        }
        if (!ok) continue;
        int cur = a[pos] - '0';
        auto it = d.lower_bound(cur);
        if (it == d.begin()) continue;
        --it;
        int x = *it;
        if (pos == 0 && an > 1 && x == 0) continue;
        s.pb(char('0' + x));
        FOR(i, pos + 1, an) s.pb(char('0' + mxint));
        if (best.empty() || stoll(s) > stoll(best)) best = s;
    }
    bool same = true;
    F0R(i, an) if (!d.count(a[i] - '0')) same = false;
    if (same) best = a;
    return best;
}

string build_upper(string a, set<int>& d, int mnint) {
    int an = a.length();
    string best = "";
    F0R(pos, an) {
        string s = "";
        bool ok = true;
        F0R(i, pos) {
            int cur = a[i] - '0';
            if (!d.count(cur)) ok = false;
            s.pb(a[i]);
        }
        if (!ok) continue;
        int cur = a[pos] - '0';
        auto it = d.upper_bound(cur);
        if (it == d.end()) continue;
        int x = *it;
        if (pos == 0 && an > 1 && x == 0) continue;
        s.pb(char('0' + x));
        FOR(i, pos + 1, an) s.pb(char('0' + mnint));
        if (best.empty() || stoll(s) < stoll(best)) best = s;
    }
    bool same = true;
    F0R(i, an) if (!d.count(a[i] - '0')) same = false;
    if (same) best = a;
    return best;
}

long long longbuilder(string s, string a) {
    if (s.empty()) return LLONG_MAX;
    if (s.length() > 1 && s[0] == '0') return LLONG_MAX;
    long long x = stoll(s), y = stoll(a);
    return llabs(x - y);
}

void solve(int tc) {
    string a;
    int n;
    cin >> a >> n;
    set<int> d;
    int mxint = 0, mnint = 10, mn0 = 10;
    F0R(i, n) {
        int x; cin >> x; d.insert(x);
        mxint = max(mxint, x);
        mnint = min(mnint, x);
        if (x != 0) mn0 = min(mn0, x);
    }
    long long ans = LLONG_MAX;
    ans = min(longbuilder(build_lower(a, d, mxint), a), ans);
    ans = min(longbuilder(build_upper(a, d, mnint), a), ans);
    int an = a.length();
    if (an > 1) {
        string s = "";
        F0R(i, an - 1) s.pb(char('0' + mxint));
        ans = min(longbuilder(s, a), ans);
    }
    if (mn0 != 10) {
        string s = "";
        s.pb(char('0' + mn0));
        F0R(i, an) s.pb(char('0' + mnint));
        ans = min(longbuilder(s, a), ans);
    }
    cout << ans << '\n';
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}