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
#define int long long

void solve() {
    int k, n, sum = 0, c = 0;
    string s;
    cin >> k >> s;
    n = s.length();
    vi z;
    F0R (i, n) {
        if (s[i] == '1') {
            z.pb(c+1);
            c = 0;
        } else {
            c++;
        }
    }
    z.pb(c+1);
    if (k) FOR(i, k, z.size()) sum += z[i]*z[i-k];
    else F0R(i, z.size()) sum += ((z[i]-1)*z[i])>>1;
    cout << sum << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}