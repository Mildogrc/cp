#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int ans;
    cout << "? " << 1 << " " << 2 << "\n";cout.flush();
    cin >> ans;
    if (ans) {
        cout << "! " << 1 << "\n";cout.flush();
        return;
    }
    cout << "? " << 2 << " " << 3 << "\n";cout.flush();
    cin >> ans;
    if (ans) {
        cout << "! " << 2 << "\n";cout.flush();
        return;
    }
    cout << "? " << 1 << " " << 3 << "\n";cout.flush();
    cin >> ans;
    if (ans) {
        cout << "! " << 1 << "\n";cout.flush();
        return;
    }

    for(int i = 5; i < 2*n; i+=2) {
        cout << "? " << (i-1) << " " << i << "\n";cout.flush();
        cin >> ans;
        if (ans) {
            cout << "! " << i << "\n";cout.flush();
            return;
        }
    }
    cout << "! "  << 2*n << "\n";cout.flush();

}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}