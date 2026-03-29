#include <bits/stdc++.h>
using namespace std;
// #define int long long

void printms(multiset<int> my_multiset) {
    std::cout << "Elements of the multiset: ";
    for (const auto& element : my_multiset) {
        std::cout << element << " ";
    }
    std::cout << std::endl;
}

void solve() {
    int n, k;
    cin >> n >> k;
    vector<int> a(n);
    vector<int> b(n);

    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < n; i++) cin >> b[i];

    multiset<int> m;
    for (int i = 0; i < k; i++) m.insert(a[i]);
    for (int i = 0; i < n-k; i++) {
        printms(m);
        if (b[i]!=-1 && m.count(b[i]) == 0) {
            cout << "NO\n";
            return;
        }
        m.erase(m.find(a[i]));
        m.insert(a[i+k]);
    }
    printms(m);
    for (int i = n-k; i < n; i++) {
        if (b[i]!=-1 && m.count(b[i]) == 0) {
            cout << "NO\n";
            return;
        }
    }
    cout << "YES\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}