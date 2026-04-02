#include <bits/stdc++.h>
using namespace std;

void solve() {
    unordered_map<int, int> s;
    int n;
    string ret = "YES";
    cin >> n;

    for(int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            int a;
            cin >> a;
            if (++s[a] > n * n - n) ret = "NO";
        }
    }
    cout << ret << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}