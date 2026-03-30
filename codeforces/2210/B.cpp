#include <bits/stdc++.h>
using namespace std;
// #define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    int mx = 0;
    int curr = 0;
    vector<int> sat(n);
    for (int i = 0; i < n; i++) {
        if (sat[i]) {
            sat[i] = 0;
            --curr;
        }
        sat[a[i]-1] = 1;
        curr++;
        mx = max(curr, mx);
    }
    cout << mx << "\n";
    
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}