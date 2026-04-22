#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define pb push_back
#define V vector
#define vi V<int>
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
// #define int long long
// #define double long double

void solve() {
    string s;
    cin >> s;
    int n = s.length();
    int c = 0;
    FOR(i, 1, n) if (s[i] == s[i-1]) c++;
    if (c <= 2) cout << "YES\n";
    else cout << "NO\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}