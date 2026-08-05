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

int count(string &a, string &b, int n, int p1 = 0, int p2 = 0) {
    int sum = 0;
    while (p1 < n && p2 < n) {
        if (a[p1] == '1' && b[p2] == '1') {
            sum += abs(p1-p2);
            p1 += 2;
            p2 += 2;
        } else {
            if (a[p1] == '0') p1 += 2;
            if (b[p2] == '0') p2 += 2;
        }
    }
    ifD cout << a << "-" << p1 << ": " << sum << endl;
    return sum;
}

void solve(int tc) {
    int n, k = 0;
    cin >> n;
    string a, b;
    cin >> a >> b;
    int ae = 0, ao = 0, be = 0, bo = 0;
    for(int i = 0; i < n; i += 2) {
        ae += a[i]-'0';
        be += b[i]-'0';
    }
    for(int i = 1; i < n; i += 2) {
        ao += a[i]-'0';
        bo += b[i]-'0';
    }
    ifD cout << ae << " " << ao << " " << be << " " << bo << ": ";
    if (ae == be && ao == bo) {
        cout << (count(a, b, n) + count(a, b, n, 1, 1))/2 << endl;
    } else {
        cout << -1 << endl;
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}