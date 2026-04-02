#include <bits/stdc++.h>
using namespace std;

void solve() {
    int x, y;
    cin >> x >> y;
    int sum = (y >= 0) ? 2 * y : 4 * -y;
    int zero_start = x-sum;
    if (zero_start >= 0 && zero_start % 3 == 0) cout << "YES";
    else cout << "NO";
    cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}