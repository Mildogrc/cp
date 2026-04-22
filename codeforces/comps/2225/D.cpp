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
// #define double long double
const int MOD = 998244353;

int prod(int a, int b) {
    return ((a%MOD)*(b%MOD))%MOD;
}

int sum(int a, int b) {
    return ((a%MOD)+(b%MOD))%MOD;
}

void solve() {
    int n, k;
    cin >> n >> k;
    int c = k/4+1;
    int e = (n+1)/4+1;
    int f = (k+2)/4;
    int g = (n+3)/4;
    int d = e-c;
    int a = prod(c, d);
    int b = prod(f, g-f);
    cout << sum(a, b) << '\n';

}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}