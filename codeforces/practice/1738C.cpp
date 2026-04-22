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
    int n;
    cin >> n;
    vi a(n);
    F0R(i, n) cin >> a[i];
    int e = 0, o = 0;
    F0R(i, n) o += (a[i]%2);
    e = n-o;
    if (e%2 == 1) o--;
    // cout << o << endl;
    if (((o+1)/2) %2 == 0) cout << "Alice\n";
    else cout << "Bob\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}