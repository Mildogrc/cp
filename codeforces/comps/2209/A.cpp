#include <bits/stdc++.h>
using namespace std;
#define int long long

void solve() {
    int n, c, k;
    cin >> n >> c >> k;
    vector<int> a(n);
    for(int i = 0; i < n; i++)cin>>a[i];
    sort(a.begin(), a.end());
    // for(int i = 0; i < n; i++) cout << a[i] << " ";cout << endl;
    for(int i = 0; i < n; i++) {
        if (a[i] > c) break;
        if (a[i] < c) {
            if(a[i] + k >= c) {
                k -= c-a[i];
                a[i] = c;
            } else {
                a[i] += k;
                k=0;
            }
        }
        c += a[i];
    }
    // for(int i = 0; i < n; i++) cout << a[i] << " ";cout << endl;
    // cout << "ans: ";
    cout << c << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}