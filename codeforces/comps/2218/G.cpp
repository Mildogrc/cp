#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long


int solve() {
    int n, m;
    cin >> n >> m;

    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 1; i < n-1; i++) {
        if(a[i] && a[i-1]>=a[i]<=a[i+1]) {
            return 0;
        }
    }

    vector<vector<int>> count(m);
    for (int i = 0; i < n; i++) count[a[i]].push_back(i);
    
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) cout << solve() << "\n";
}