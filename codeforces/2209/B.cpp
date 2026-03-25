#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    vector<int> out(n);
    for(int i = 0; i < n; i++) cin >> a[i];

    for(int i = 0; i < n; i++) {
        int less = 0;
        int more = 0;
        for (int j = i + 1; j < n; j++) {
            if (a[i] < a[j]) less++;
            if (a[i] > a[j]) more++;
        }
        if (less < more) {
            out[i] = more;
        } else {
            out[i] = less;
        }
    }
    for(int i = 0; i < n; i++) cout << out[i] << " "; cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}