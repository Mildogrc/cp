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
        F0R(i, n) F0R(j, m) sum[i] += a[i][j];

        set<int> s;
        F0R(i, n) if (sum[i] < target) s.insert(i);
        // cout << "set of less: "; for(int e : s) cout << e << " "; cout << endl;
        V<tuple<int, int, int>> ops;
        F0R(i, n) {
            int j = 0;
            // if (sum[i] > target) cout << "working on: " << i << endl;
            while (sum[i] > target) {
                // cout << "here1" << endl;
                if (a[i][j]) {
                    for (int x : s) {
                        if(a[x][j] == 0) {
                            ops.pb(make_tuple(i, x, j));
                            a[i][j] = 0; sum[i]--;
                            a[x][j] = 1; sum[x]++;
                            if (sum[x] == target) s.erase(x);
                            break;
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