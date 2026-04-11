#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    vector<int> c(n);
    c[n-1]=1;
    for (int i = n-2; i >= 0; i--) {
        c[i] += c[i+1];
        if (s[i]==')') c[i]++;
    }
    int first_close = 0;
    while (s[first_close] == '(') first_close++;

    int o = 0;
    int mx = 0;
    for (int i = 0; c[i] >= o && i<n-1; i++) {
        if (s[i] == '(') o++;
        if (o <= c[i+1]) mx = max(mx, o);
    }
    if (first_close < mx) {
        cout << (mx<<1) << "\n";
    } else cout << "-1\n";

    for (int i = 0; i < n; i++) cout << c[i] << " "; cout << endl;
    cout << first_close << endl;
    cout << mx << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}