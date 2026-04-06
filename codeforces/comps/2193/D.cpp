#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long

int bsearch(vector<int> &b, int x, int n) {
    int l = 0, r = n-1;
    while (l < r) {
        int m = (l + r + 1)>>1;
        if (b[m] > x) {
            r = m-1;
        } else {
            l = m;
        }
    }
    return l+1;
}

void solve() {
    int n;
    cin >> n;
    vector<int> a(n), b(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < n; i++) cin >> b[i];
    for (int i = 1; i < n; i++) b[i] += b[i-1];
    sort(a.begin(), a.end());
    unordered_map<int, int> m;
    for (int i = 0; i < n; i++) m[a[i]]++;
    
    int swords = n;
    int mx = 0;
    for (int i = 0; i < n; i++) {
        if (m[a[i]]) {
            int x = a[i];
            int levels = 0;
            if (swords >= b[0]) levels = bsearch(b, swords, n);
            mx = max(mx, x*levels);
            // cout << x << " " << levels << " " << mx << endl;
            swords -= m[x];
            m[x] = 0;
        }
    }
    cout << mx << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}