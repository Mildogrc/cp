#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

int follows(string &s, vector<int> &a, int n) {
    if (s.length() != n) return 0;
    map<char, int> m;
    map<int, char> m2;
    for (int i = 0; i < n; i++) {
        if (m.contains(s[i]) && m[s[i]] != a[i]) return 0;
        if (m2.contains(a[i]) && m2[a[i]] != s[i]) return 0;
        m[s[i]] = a[i]; 
        m2[a[i]] = s[i];
    }
    return 1;
}

void solve() {
    int n, m;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    cin >> m;
    while (m--) {
        string s;
        cin >> s;
        map<char, int> m;
        if (follows(s, a, n)) cout << "YES\n";
        else cout << "NO\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}