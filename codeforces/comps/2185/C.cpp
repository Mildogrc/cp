#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    sort(a.begin(), a.end());
    int mxlen = 1;
    int currlen = 1;
    for (int i = 1; i < n; i++) {
        if (a[i] != a[i-1]) {
            if (a[i]-a[i-1] == 1) {
                mxlen = max(mxlen, ++currlen);
            } else {
                currlen = 1;
            }
        }
    }
    cout << mxlen << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}