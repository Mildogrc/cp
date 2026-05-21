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

int all_digits(string s, set<int> ds) {
    F0R(i, s.length()) if (!ds.contains(s[i]-'0')) return 0; return 1;
}

long long diff(string a, string b) {
    return llabs(stoll(a) - stoll(b));
}

long long min_diff(string a, string x, int c1, int c2) {
    if (c1 >= 0) x += (c1 + '0');
    long long mn = diff(a, x);
    ifD cout << x << endl;
    while (x.length() <= 18) {
        mn = min(mn, diff(a, x));
        x += (c2 + '0');
        ifD cout << x << endl;
    }
    return mn;
}

int lub(int x, vi d, int z) {
    int n = d.size();
    if (z == 0) F0R(i, n) if (d[i] == x) return x;
    if (z > 0) F0R(i, n) if (d[i] > x) return d[i];
    if (z < 0) R0F(i, n) if(d[i] < x) return d[i];
    return -1;
}

void solve(int tc) {
    string a;
    int n;
    cin >> a >> n;

    vi d(n); F0R(i, n) cin >> d[i];
    set<int> ds; F0R(i, n) ds.insert(d[i]);

    if (all_digits(a, ds)) {
        cout << 0 << endl;
        return;
    }
    if (n == 1) {
        string x = "";
        long long mn = min_diff(a, x, d[0], d[0]);
        cout << mn << endl;
    } else {
        int mx = d[n-1], mn = d[0], mn0 = d[0];
        if (!mn) mn = d[1];
        string x = "";
        long long mn_val = min_diff(a, x, mx, mx);
        x = "";
        mn_val = min(mn_val, min_diff(a, x, mn, mn0));
        x = "";
        F0R(i, a.length()) {
            vi p(3); F0R(j, 3) p[j] = lub(a[i]-'0', d, j-1);
            ifD F0R(j, 3) cout << p[j] << " \n"[j==2];
            if (p[0] >= 0) mn_val = min(mn_val, min_diff(a, x, p[0], mx));
            if (p[2] >= 0) mn_val = min(mn_val, min_diff(a, x, p[2], mn0));
            if (p[1] == -1) break;
            x += (p[1] + '0');
            ifD cout << x << endl;
        }

        cout << mn_val << endl;
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}