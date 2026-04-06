#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long

int prods(set<int> &s, int x, vector<int> &dp) {
    // cout << "prods(" << x << ")" << endl;
    if (dp[x] != 0) return dp[x];
    int i;
    int mx = INT_MAX;
    for (i = 2; i * i <= x; i++) {
        if (x%i == 0 && s.contains(x/i)) {
            int rest = prods(s, i, dp);
            if (rest != -1) mx = min(1 + rest, mx);
        }
    }
    while (i > 1) {
        if (x%i == 0 && s.contains(i)) {
            if (x%i == 0 && s.contains(i)) {
                int rest = prods(s, x/i, dp);
                if (rest != -1) mx = min(1 + rest, mx);
            }
        }
        i--;
    }
    dp[x] = mx;
    if (mx == INT_MAX) return -1;
    return mx;
}

void solve() {
    int n;
    cin >> n;
    set<int> s;
    vector<int> dp(n+1);
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        s.insert(x);
        dp[x]=1;
    }
    for (int i = 1; i<= n; i++) cout << prods(s, i, dp) << " ";
    cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}