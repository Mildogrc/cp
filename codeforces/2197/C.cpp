#include <bits/stdc++.h>
using namespace std;
#define ll long long

int solve() {
    ll p, q;
    cin >> p >> q;
    if (p >= q) return 1;

    ll l = 0;
    ll r = p;

    while (l < r) {
        ll mid = (l+r)/2;
        // cout << p-mid << " " << q-mid << "\n";
        if ((p-mid) * 3 > (q-mid) * 2) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    // cout << p-l << " " << q-r << "\n";
    if ((p-l) * 3 == (q-r) * 2) return 0;
    return 1;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) cout << (solve() ? "Alice\n" : "Bob\n");
}