#include <bits/stdc++.h>
using namespace std;
// #define int long long

void solve() {
    int n;
    cin >> n;
    int a;
    for (int i = 0; i < n; i++) cin >> a;
    if (n == 1) {
        cout << "1\n";
    } else {
        for (int i = 0; i < n; i++) cout << "2 ";
        cout << "\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}