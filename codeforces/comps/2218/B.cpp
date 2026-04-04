#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    vector<int> a(7);
    for(int i = 0; i < 7; i++) cin >> a[i];
    int sum = 0;
    for (int i = 0; i < 7; i++) sum += a[i];
    int mx = 2*a[0] - sum;
    for (int i = 1; i < 7; i++) mx = max(mx, 2*a[i] - sum);
    cout << mx << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}