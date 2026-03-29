#include <bits/stdc++.h>
using namespace std;
// #define int long long

void printms(multiset<int> my_multiset) {
    
}

void solve() {
    int n, k;
    cin >> n >> k;
    vector<int> a(n);
    vector<int> b(n);

    vector<int> c(n);

    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < n; i++) cin >> b[i];

    for (int i = 0; i < n; i++) {
        if (b[i] > n) {
            cout << "NO\n";return;
        }
        if (b[i] != -1) {
            if (++c[b[i]-1] > 1) {
                cout << "NO\n";return;
            }
        }
    }

    if (k-1 > n-k) {
        multiset<int> m;
        for (int i = n-k; i < k; i++) {
            m.insert(a[i]);
        }
        // cout << "m: "; for (const auto& e : m) {cout << e << " ";}
        // cout << endl;
        for (int i = n-k; i < k-1; i++) {
            if (b[i] != -1 && m.count(b[i]) == 0) {
                cout << "NO\n";return;
            }
        }
        for (int i = 0; i < n-k; i++) {
            if (b[i] != -1 && a[i] != b[i]) {
                cout << "NO\n";return;
            }
        }
        for (int i = k; i < n; i++) {
            if (b[i] != -1 && a[i] != b[i]) {
                cout << "NO\n";return;
            }
        }
    } else {
        for (int i = 0; i < n; i++) {
            if (b[i] != -1 && a[i] != b[i]) {
                cout << "NO\n";return;
            }
        }
    }
    
    cout << "YES\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}