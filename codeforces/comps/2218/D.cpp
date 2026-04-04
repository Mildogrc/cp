#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long

int prime(int x) {
    for (int i = 2; i*i <= x; i++) {
        if (x%i == 0) return 0;
    }
    return 1;
}

void solve() {
    int n;
    cin >> n;
    int i = 0;
    int p = 1;
    int k = 2;
    while (i < n) {
        if (prime(k)) {
            cout << (k*p) << " ";
            p = k;
            i++;
        }
        k++;
    }
    cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}