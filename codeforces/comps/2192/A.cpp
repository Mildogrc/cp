#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    int c = 1;
    int mul = 0;
    for (int i = 1; i < n; i++) {
        if (s[i] == s[i-1]) mul = 1;
        else c++;
    }
    if(s[0] != s[n-1] && mul) c++;
    cout << c+1 << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}