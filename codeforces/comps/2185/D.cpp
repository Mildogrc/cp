#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n, m, h, b, c;
    cin >> n >> m >> h;
    vector<int> a(n), o(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < n; i++) o[i] = a[i];
    set<int> r;
    for (int i = 0; i < m; i++) {
        cin >> b >> c;
        int curr = a[--b];
        if (!r.contains(b)) curr = o[b];
        a[b] = curr + c;
        if (a[b] > h) {
            r.clear();
            a[b] = o[b];
        }
        r.insert(b);
    }
    for (int i = 0; i < n; i++) {
        if (r.contains(i)) {
            cout << a[i] << " ";
        } else {
            cout << o[i] << " ";
        }
    }
    cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}