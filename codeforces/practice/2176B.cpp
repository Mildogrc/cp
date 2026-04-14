#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n; string s;
    cin >> n >> s;
    int st = 0;
    while (st < n && s[st] == '0') st++;
    if (st == n) cout << "0\n";
    else {
        int l = 0;
        int mx = 0;
        for (int i = 0; i < n; i++) {
            if (s[(st+i)%n] == '1') {
                l = i;
            } else {
                mx = max(mx, i-l);
            }
        }
        cout << mx << "\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}