#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define pb push_back
#define V vector
#define vi V<int>
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
// #define int long long
// #define double long double

void solve() {
    int n, m, s = 0;
    cin >> n >> m;
    V<vi> a(n, vi(m));
    F0R(i, n) F0R(j, m) cin >> a[i][j];
    F0R(i, n) F0R(j, m) s += a[i][j];
    if (s%n) {
        cout << "-1\n";
    } else {
        int target = s/n;
        vi sum(n);
        set<int> s;
        V<set<int>> zeros(m);
        V<tuple<int, int, int>> ops;
        
        F0R(i, n) F0R(j, m) sum[i] += a[i][j];
        F0R(i, n) if (sum[i] < target) s.insert(i);
        F0R(j, m) for(int i : s) if (a[i][j] == 0) zeros[j].insert(i);
        F0R(i, n) {
            int j = 0;
            while (sum[i] > target) {
                if (a[i][j]) {
                    if (zeros[j].size() > 0) {
                        int x = *zeros[j].begin();
                        ops.pb(make_tuple(i, x, j));
                        a[i][j] = 0; sum[i]--;
                        a[x][j] = 1; sum[x]++;
                        zeros[j].erase(x);
                        if (sum[x] == target) { //s.erase(x);
                            F0R (r, m) zeros[r].erase(x);
                        }
                    }
                }
                j++;
            }
        }
        int o = ops.size();
        cout << o << "\n";
        F0R(i, o) cout << ++get<0>(ops[i]) << " " << ++get<1>(ops[i]) << " " << ++get<2>(ops[i]) << "\n";
    }
    // F0R(i, n) {F0R(j, m) cout << a[i][j]; cout << endl;} cout << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}