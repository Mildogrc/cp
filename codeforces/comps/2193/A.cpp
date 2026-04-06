#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n, x, s;
    cin >> n >> s >> x;
    int sum = 0;
    for (int i = 0; i < n; i++) {
        int j;
        cin >> j;
        sum += j;
    }
    if (sum > s) {
        cout << "NO\n";
    } else if ((s-sum)%x != 0) {
        cout << "NO\n";
    } else {
        cout << "YES\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}