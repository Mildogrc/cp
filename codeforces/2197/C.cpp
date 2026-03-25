#include <bits/stdc++.h>
using namespace std;
#define ll long long

int solve() {
    ll p, q;
    cin >> p >> q;
    if (p > q) return 1;

    ll l = 0;
    ll r = p;

    while (l < r) {
        cout << l << " " << r << "\n";
        ll mid = (l+r+1)/2;
        if ((p-mid) * 3 < (q-mid) * 2) {
            r = mid - 1;
        } else {
            l = mid;
        }
    }
    if ((p-l) * 3 == (q-r) * 2) return 0;
    return 1;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) cout << (solve() ? "Alice\n" : "Bob\n");
}