#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n, m, a, b;
    cin >> n >> m >> a >> b;
    if (gcd(n, a%n) * gcd(m, b%m) == 1 && gcd(n, m) <= 2){
        cout << "YES\n";
    } else {
        cout << "NO\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}