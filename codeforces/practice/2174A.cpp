#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    string s, t;
    cin >> s >> t;
    int n = s.length(), m = t.length();
    vector<int> s_c(26), t_c(26);
    for (int i = 0; i < n; i++) s_c[s[i]-'a']++;
    for (int i = 0; i < m; i++) t_c[t[i]-'a']++;

    int f = 0;
    for (int i = 0; i < 26; i++) if (s_c[i] > t_c[i]) f = 1;
    if (f) {
        cout << "Impossible\n";
    } else {
        for (int i = 0; i < 26; i++) t_c[i] -= s_c[i];
        int j = 0;
        for (int i = 0; i < n; i++) {
            for (; j < (s[i]-'a'); j++) {
                while (t_c[j]-->0) cout << (char) (j+'a');
            }
            cout << s[i];
        }
        for (; j < 26; j++) while (t_c[j]-->0) cout << (char) (j+'a');
        cout << "\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}