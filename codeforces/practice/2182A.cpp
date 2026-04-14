#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    string s;
    cin >> n >> s;
    if (s.contains("2026") || !s.contains("2025")) cout << "0\n";
    else cout << "1\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}