#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void print_path(int i, int j, int n) {
    // cout << i << " " << j << " " << n << endl;
    for (int x = 0; x < i; x++) cout << "D";
    for (int x = 0; x < j; x++) cout << "R";
    cout << "D";
    for (int x = j; x < n; x++) cout << "R";
    cout << "\n";
}

void solve() {
    int n, m;
    cin >> n >> m;
    vector<vector<int>> a(n, vector<int>(m));
    for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) cin >> a[i][j];
    int sum = 0, secsum = 0;
    for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) sum += a[i][j];
    for (int i = 0; i < n; i++) {
        for (int j = m-1; j >=0; j--) {
            secsum += a[i][j];
            if (secsum == sum/2) {
                cout << secsum * (sum-secsum) << "\n";
                print_path(i, j, n);
                return;
            }
        }
    }

}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}