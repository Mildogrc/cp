#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    int c = 0;
    for (int i = 0; i < n; i++) c+= (s[i]-'0');
    if (c%2 == 0) {
        cout << c << "\n";
        for (int i = 0; i < n; i++) if (s[i] == '1') cout << (i+1) << " ";
        if (c) cout << "\n";
    } else if ((n-c)%2) {
        int x = n-c;
        cout << x << "\n";
        for (int i = 0; i < n; i++) if (s[i] == '0') cout << (i+1) << " ";
        if (x) cout << "\n";
    } else {
        cout << "-1\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}