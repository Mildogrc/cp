#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long

void solve() {
    int n, x, y;
    cin >> n >> x >> y;
    vector<int> a(n), b(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    int sum = 0;
    for (int i = 0; i < n; i++) {
        b[i] = (a[i]/x) * y;
        sum += b[i];
    }

    int mx = a[0] + (sum-b[0]);
    for (int i = 0; i < n; i++) mx = max(mx, a[i] + (sum-b[i]));

    cout << mx << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}