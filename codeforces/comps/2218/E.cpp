#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    int mx = a[0]^a[1];
    for (int i = 0; i < n; i++) for (int j = i + 1; j < n; j++) mx = max(mx, a[i]^a[j]);
    cout << mx << '\n';
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}