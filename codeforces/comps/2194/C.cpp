#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void print_base(vector<int> &base, int n, int d) {
    for (int i = 0; i < n; i++) cout << char('a' + (base[i%d]));
    cout << "\n";
}

void solve() {
    int n, k;
    cin >> n >> k;
    vector<string> s(k);
    for (int i = 0; i < k; i++) cin >> s[i];

    vector<vector<int>> count(n, vector<int>(26));

    for (int i = 0; i < k; i++) for (int j = 0; j < n; j++) count[j][s[i][j] - 'a']++;

    set<int> divs;
    for (int i = 1; i * i <= n; i++) {
        if (n%i==0){
            divs.insert(i);
            divs.insert(n/i);
        }
    }

    for (const auto& d : divs) {
        vector<int> base(d, -1);
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < 26; j++) {
                base[i] = j;
                for (int k = i; k < n; k+= d) {
                    if (count[k][j] == 0) {
                        base[i] = -1;
                        break;
                    }
                }
                if (base[i] != -1) break;
            }
            int done = 1;
            for (int i = 0; i < d; i++) if (base[i] == -1) done = 0;
            if (done) {
                print_base(base, n, d);
                return;
            }
        }
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}