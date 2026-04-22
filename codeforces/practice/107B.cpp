#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define pb push_back
#define V vector
#define vi V<int>
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
#define int long long
#define double long double

void solve() {
    int n, m, h, sum = 0;
    cin >> n >> m >> h;
    vi a(m);
    F0R(i, m) cin >> a[i];
    a[--h]--;
    F0R(i, m) sum += a[i];
    n--;
    if (sum < n) {
        cout << "-1\n";
    } else {
        double ans = 1.0;
        F0R(i, n) {
            double t = (sum - a[h]), b = sum--;
            ans *= (t/b);
        }
        cout << setprecision(10) << (1-ans) << endl;
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}