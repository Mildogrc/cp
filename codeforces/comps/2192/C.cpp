#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long


void solve() {
    int n, h, k;
    cin >> n >> h >> k;

    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    int sum = 0;
    for (int i = 0; i < n; i++) sum += a[i];
    int s = h/sum;
    s *= (n+k);
    h%=sum;
    if (h == 0) {
        s -= k;
        cout << s << "\n";
    } else {
        // cout << s << ":--\n";
        vector<int> mx(n), mn(n);
        mx[n-1] = a[n-1];
        mn[0] = a[0];
        for (int i = n-2; i>=0; i--) mx[i] = max(mx[i+1], a[i]);
        for (int i = 1; i < n; i++) mn[i] = min(mn[i-1], a[i]);
        // for (int i = 0; i < n; i++) cout << mx[i] << " "; cout << "\n";
        // for (int i = 0; i < n; i++) cout << mn[i] << " "; cout << "\n";
        sum = 0;
        int i;
        for (i = 0; i < n-1; i++) {
            sum += a[i];
            if (sum >= h) break;
            if (sum - mn[i] + mx[i+1] >= h) break;
        }
        cout << s + i + 1 << "\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}