#include <bits/stdc++.h>
using namespace std;
#define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> path_lcm(n);
    vector<int> path_count(n);
    for (int i = n-1; i >=0; i--) {
        int a, k;
        cin >> a >> k;
        int paths = 0;
        int l = 1;
        for (int j = 0; j < k; j++) {
            int c;
            cin >> c;
            paths += path_count[--c];
            if (gcd(path_lcm[c], a) > 1) l = lcm(l, gcd(path_lcm[c], a));
        }
        if (gcd(a, l) > 1) {
            path_lcm[i] = gcd(a, l);
            path_count[i] = paths;
        } else {
            path_lcm[i] = a;
            path_count[i] = paths+1;
        }
        cout << path_count[i] << endl;
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}