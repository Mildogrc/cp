#include <bits/stdc++.h>
using namespace std;
// #define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    set<pair<int, int>> s;
    for (int i = 0; i < n; i++) {
        for (int x = 1; x <= a[i]; x++) {
            int j = i-x*a[i];
            if (j < 0) break;
            // cout << j << "=/=" << i << endl;
            if (a[i]*a[j] == i-j) {
                // cout << j << "==" << i << endl;
                s.insert(make_pair(j, i));
            }
        }
        for (int x = 1; x <= a[i]; x++) {
            int j = i+x*a[i];
            if (j >= n) break;
            // cout << j << "=/=" << i << endl;
            if (a[i]*a[j] == j-i) {
                // cout << j << "==" << i << endl;
                s.insert(make_pair(i, j));
            }
        }
    }
    // cout << "s: ";for (const auto& item : s) { cout << item.first << "-" << item.second << " ";}
    // std::cout << std::endl;
    cout << s.size() << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}