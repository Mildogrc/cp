#include <bits/stdc++.h>
using namespace std;
// #define int long long

void solve() {
    int x, y;
    cin >> x >> y;
    cout << max(1, abs(x-y)) << "\n";
    for (int i = 0; i < x; i++) cout << "1 ";
    for (int i = 0; i < y; i++) cout << "-1 ";
    cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}