#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
// #define int long long

string string_xor(string a, const string &b) {
    F0R(i, a.length())
        if (a[i] == b[i]) a[i] = '0';
        else a[i] = '1';
    return a;
}

void solve() {
    string s;
    cin >> s;
    int n = s.length();
    cout << "1 " << n << " ";
    int f = 0;
    while (f < n && s[f] == '1') f++;
    if (f == n) {
        cout << "1 1\n";
    } else {
        int l = n-f;
        string mx = string_xor(s.substr(0, l), s.substr(f, l));
        int mxi = 0;
        F0R(i, f) {
            string curr = string_xor(s.substr(i, l), s.substr(f, l));
            if (curr > mx) {
                mx = curr;
                mxi = i;
            }
        }
        cout << (mxi+1) << " " << (mxi+l) << "\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}