#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define V vector
#define vi V<int>
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
// #define int long long

void solve() {
    int n, m, r;
    cin >> n >> m;
    vi a(n);
    F0R(i, n) cin >> a[i];
    int x = n-1;
    while (x > 0 && a[x] == x+1) x--;
    x++;
    // cout << x << endl;
    double ans = 1;
    F0R(_, m) {
        double p;
        cin >> r >> p;
        if (r >= x) ans *= (1-p);
    }
    // cout << "ANSWER: ";
    if (x == 1) cout << "1\n";
    else cout << (1-ans) << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    cout << setprecision(10);
    int t; cin >> t; while (t--) solve();
}