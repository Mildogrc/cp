#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n, q;
    cin >> n >> q;
    vector<int> a(n+1), b(n+1);
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < n; i++) cin >> b[i];
    for (int i = n-1; i >= 0; i--) a[i] = max(a[i], max(a[i+1], b[i]));

    for (int i = 1; i < n; i++) a[i] += a[i-1];
    for (int i = 0; i < q; i++) {
        int l, r;
        cin >> l >> r;
        l--;r--;
        if (l) cout << (a[r]-a[l-1]);
        else cout << a[r];
        cout << " ";
    }
    cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}