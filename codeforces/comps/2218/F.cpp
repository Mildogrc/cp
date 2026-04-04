#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int x, y;
    cin >> x >> y;
    if (x > y || x+(y&1) == 0 ) {
        cout << "NO\n";
    } else {
        cout << "YES\n";
        int k = x+y;
        while (y > x + 1) {
            cout << "1 " << k-- << "\n";
            cout << "1 " << k-- << "\n";
            y-=2;
        }
        for (int i = 1; i < x+y; i++) {
            cout << i << " " << i + 1 << "\n";
        }
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}