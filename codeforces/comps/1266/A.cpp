#include <bits/stdc++.h>
using namespace std;
// #define int long long

void solve() {
    string x;
    cin >> x;
    vector<int> c(10);
    for(int i = 0; x[i] != '\0'; i++) c[x[i] - '0']++;
    if (c[0]-- == 0) {
        cout << "cyan\n";
        return;
    }
    int flag = 1;
    for (int i = 0; i < 10; i+=2) if (c[i]>0) flag = 0;
    if (flag) {
        cout << "cyan\n";
        return;
    }

    int sum = 0;
    for (int i = 0; i < 10; i++) sum += i * c[i];

    if (sum % 3 != 0) {
        cout << "cyan\n";
        return;
    }

    cout << "red\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}