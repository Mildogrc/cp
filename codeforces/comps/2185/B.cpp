#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n, mx;
    cin >> n;
    vector<int> a(n);
    cin >> mx;
    for (int i = 1; i < n; i++) {
        int x;
        cin >> x;
        mx = max(mx, x);
    }
    cout << mx*n << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}