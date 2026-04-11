#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n, x;
    cin >> n;
    vector<int> a(101);
    for (int i = 0; i < n; i++) {
        cin >> x;
        a[x]++;
    }
    if (a[0] > 0 && (a[0] == 1 || a[1] > 0)) {
        cout << "YES\n";
    } else {
        cout << "NO\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}