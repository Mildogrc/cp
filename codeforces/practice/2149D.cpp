#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
#define int long long

int min_swap(string &s, char c, int n) {
    vector<int> l(n), r(n);
    int sum = 0;
    F0R(i, n) {
        l[i] = sum;
        if (s[i] == c) sum++;
    }
    sum = 0;
    R0F(i, n) {
        r[i] = sum;
        if (s[i] == c) sum++;
    }
    sum = 0;
    F0R(i, n) if (s[i] == c) sum += min(i - l[i], n-1-i - r[i]);
    // F0R(i, n) cout << l[i] << " "; cout << endl;
    // F0R(i, n) cout << r[i] << " "; cout << endl;
    // cout << sum << endl;
    return sum;
}

void solve() {
    int n;
    string s;
    cin >> n >> s;
    int ans = min(min_swap(s, 'a', n), min_swap(s, 'b', n));
    // cout << "sum: ";
    cout << ans << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}