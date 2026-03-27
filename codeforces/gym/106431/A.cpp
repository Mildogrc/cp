#include <bits/stdc++.h>
using namespace std;
#define int long long

vector<int> createDP() {
    vector<int> dp(71);
    dp[0] = 1L;
    for (int i = 1L; i < 71L; i++) {
        dp[i] += dp[i-1];
        if (i >= 2) dp[i] += dp[i-2];
        if (i >= 4) dp[i] += dp[i-4];
    }
    // dp[0] = 0L;
    // dp[1] = 0L;
    return dp;
}

void solve(string &x, vector<int> &dp) {
    int e = 0L;
    int sum = 1L;
    for (int i = 0; i < x.length(); i++) {
        if (x[i] == '-') {
            ++e;
        } else {
            sum *= dp[e];
            e = 0;
        }
    }
    sum *= dp[e];
    // cout << "ans:";
    cout << sum << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    vector<int> dp = createDP();
    // for (int i = 0; i < 71; i++) cout << dp[i] << " ";
    // cout << endl;
    string x;
    while (cin >> x) {
        solve(x, dp);
    }
}