#include <bits/stdc++.h>
using namespace std;
// #define int long long

void solve() {
    int n, m;
    cin >> n >> m;
    vector<vector<int>> a(n, vector<int>(m));
    for (int i = 0; i < n; i++) for(int j = 0; j < m; j++) cin >> a[i][j];
    vector<vector<vector<int>>> dp(n, vector<vector<int>>(m, vector<int>(2, 0)));
    // cout << dp.size() << " " << dp[0].size() << " " << dp[0][0].size() << "\n";

    dp[0][0][0] = a[0][0];
    for (int i = 1; i < m; i++) dp[0][i][0] = a[0][i] + dp[0][i-1][0];
    for (int i = 1; i < n; i++) dp[i][0][0] = a[i][0] + dp[i-1][0][0];
    for (int i = 1; i < n; i++) 
        for (int j = 1; j < m; j++) 
            dp[i][j][0] = max(dp[i-1][j][0], dp[i][j-1][0]) + a[i][j];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) cout << dp[i][j][0] << " "; cout << "\n";
    }


    dp[n-1][m-1][1] = a[n-1][m-1];
    for (int i = m-2; i >=0; i--) dp[n-1][i][1] = a[n-1][i] + dp[n-1][i+1][1];
    for (int i = n-2; i >=0; i--) dp[i][m-1][1] = a[i][m-1] + dp[i+1][m-1][1];
    for (int i = n-2; i >=0; i--) 
        for (int j = m-2; j >= 0; j--) 
            dp[i][j][1] = max(dp[i+1][j][1], dp[i][j+1][1]) + a[i][j];
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) cout << dp[i][j][1] << " "; cout << "\n";
    } 

    int mx = dp[0][0][0] + dp[0][0][1] - 2*a[0][0];
    for (int i = 0; i < n; i++) for(int j = 0; j < m; j++) mx = max(dp[i][j][0] + dp[i][j][1] - 3*a[i][j], mx);
    cout << "ans:";
    cout << mx << "\n"; 
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}