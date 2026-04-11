#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    a[n-1] = 1;
    int x = 0;
    for (int i = n-2; i >= 0; i--) {
        if (!x) a[i] = a[i+1]+i+1;
        else a[i] = a[i+1]-i-1;
        x ^= 1;
    }
    for (int i = 0; i < n; i++) cout << a[i] << " "; cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}