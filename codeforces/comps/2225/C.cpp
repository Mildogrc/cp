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
    int n;
    string s1, s2;
    cin >> n >> s1 >> s2;
    vi dp(n);
    if (s1[0] != s2[0]) dp[0] = 1;
    if (n > 1) {
        dp[1] = dp[0];
        if (s1[1] != s2[1]) dp[1]++;
        int c = 0;
        if (s1[1] != s1[0]) c++;
        if (s2[1] != s2[0]) c++;
        dp[1] = min(dp[1], c);
    }
    FOR(i, 2, n) {
        dp[i] = dp[i-1];
        if (s1[i] != s2[i]) dp[i]++;
        int c = 0;
        if (s1[i] != s1[i-1]) c++;
        if (s2[i] != s2[i-1]) c++;
        dp[i] = min(dp[i], c+dp[i-2]);
    }
    cout << dp[n-1] << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}