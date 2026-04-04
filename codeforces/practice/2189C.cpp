#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void c1solve(int n) {
    cout << n-(n&1) << " ";
    for (int i = 2; i < n; i++) cout << (i^1) << " ";
    cout << 1 << "\n"; 
}

void solve() {
    int n;
    cin >> n;
    
    int x = 1;
    while (x < n) x <<=1;
    if (x == n) {
        cout << "-1\n";
    } else if (n%2) {
        c1solve(n);
    } else {
        x = n-(x>>1);
        cout << (x^1) << " ";
        for (int i = 2; i < n; i++) {
            if (i == x) cout << (n-(n&1)) << " ";
            else cout << (i^1) << " ";
        }
        cout << "1\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}