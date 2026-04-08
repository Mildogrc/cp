#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n, k, p;
    cin >> n >> k;
    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    cin >> p;

    int x = a[--p];
    int lcount = 0, rcount = 0, l = 0, r = n-1;
    while (a[r] == x && r > p) r--;
    while (a[l] == x && l < p) l++;
    // cout << l << " " << r << endl;
    for (int i = l; i < p; i++) {
        if (i == 0) lcount++;
        else if (a[i] != a[i-1]) lcount++;
    }
    for (int i = r; i > p; i--) {
        if (i == n-1) rcount++;
        else if (a[i] != a[i+1]) rcount++;
    }
    if (p > 0 && a[p] != a[p-1])lcount++;
    if (p < n-1 && a[p] != a[p+1])rcount++;
    // cout << lcount << " " << rcount << endl;
    lcount += (lcount&1);
    rcount += (rcount&1);
    int ans = max(lcount, rcount);
    cout << ans << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}