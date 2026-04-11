#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    string s;
    cin >> n >> s;
    vector<int> zero(n), one(n);
    if (s[0] == '1') one[0]++;
    if (s[n-1] == '0') zero[n-1]++;
    for (int i = 1; i < n; i++) {
        one[i] += one[i-1];
        zero[n-i-1] += zero[n-i];
        if (s[n-i-1] == '0') zero[n-i-1]++;
        if (s[i] == '1') one[i]++;
    }
    // for (int i = 0; i < n; i++) cout << zero[i] << " "; cout << endl;
    // for (int i = 0; i < n; i++) cout << one[i] << " "; cout << endl;
    for (int i = 1; i < n; i++) {
        if (one[i-1] == zero[i] && one[i-1]) {
            cout << "Alice\n";
            cout << (one[i-1]<<1) << "\n";
            for (int j = 0; j < i; j++) if(s[j] == '1') cout << (j+1) << " ";
            for (int j = i; j < n; j++) if (s[j] == '0') cout << (j+1) << " ";
            cout << "\n";
            return;
        }
    }
    cout << "Bob\n";
    
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}