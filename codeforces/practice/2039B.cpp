#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

string solve() {
    string s;
    cin >> s;
    int n = s.length();
    if (n == 1) {
        return "-1";
    } else if (s[0] == s[1]) {
        return s.substr(0, 2);
    } else if (n == 2) {
        return "-1";
    } else {
        for (int i = 2; i < n; i++) {
            if (s[i] == s[i-1]) return s.substr(i-1, 2);
            if (s[i] != s[i-2]) return s.substr(i-2, 3);
        }
    }
    return "-1";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) cout << solve() << "\n";
}