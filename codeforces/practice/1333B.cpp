#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> a(n), b(n), c(3);
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < n; i++) cin >> b[i];

    for (int i = 0; i < n; i++) {
        if (a[i] < b[i] && c[2]==0) {cout << "NO\n";return;}
        if (a[i] > b[i] && c[0]==0) {cout << "NO\n";return;}
        c[a[i]+1]++;
        // for (int i = 0; i < 3; i++) cout << c[i] << " "; cout << endl;
    }
    cout << "YES\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}