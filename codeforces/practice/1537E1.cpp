#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
// #define int long long

string sb(string s, int k) {
    while (s.length() < k) s += s;
    return s.substr(0, k);
}

void solve() {
    int n, k;
    string s;
    cin >> n >> k >> s;
    string mn = sb(s.substr(0, 1), k);
    FOR(i, 2, n+1) mn = min(mn, sb(s.substr(0, i), k));
    cout << mn << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}