#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> a(n+1), b(n), dp(n);
    for (int i = 1; i <= n; i++) cin>>a[i];
    // b[0]=a[0];
    // for (int i = 1; i < n; i++) b[i] = b[i-1]+a[i];

    unordered_map<int, int> m;
    int sum = 0, count = 0, last = 0;
    m[0]=0;
    for(int i = 1; i <= n; i++) {
        // cout << last << endl;
        sum += a[i];
        if (m.contains(sum)) last = max(m[sum] + 1, last);
        count += i-last;
        m[sum]=i;
    }
    // cout << last << endl;
    cout << count << endl;

}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}