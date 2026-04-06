#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for(int i = 0; i < n; i++) cin >> a[i];
    int f = 0;
    while (f < n && a[f] == n-f) f++;
    // cout << "f: " << f << endl;
    for (int i = 0; i < f; i++) cout << a[i] << " ";
    if (f<n) {
        int mx = f;
        for (int i = f; i < n; i++) if (a[i] > a[mx]) mx = i;

        for (int i = mx; i >= f; i--) cout << a[i] << " ";
        for (int i = mx + 1; i < n; i++) cout << a[i] << " ";
    }
    
    cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}