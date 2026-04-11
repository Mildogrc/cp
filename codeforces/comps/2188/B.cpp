#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    int sum = 0;
    if (n == 1) {
        if (s[0] == '0') cout << "1\n";
        else cout << "0\n";
        return;
    }
    if (n==2) {
        if (s[0] == '0' || s[1] == '0') cout << "1\n";
        else cout << "0\n";
        return;
    }
    s = "1" + s + "1";
    int c = 0;
    for (int i = 1; i < n; i++) {
        if (s[i]=='1'){
            sum += c/3;
            c = 0;
        } else {
            c++;
        }
    }
    
    for (int i = 0; i < count.size(); i++) {
        count[i] = max(0, count[i]);
        if (count[i] < 3) sum += 1;
        else sum += count[i]/3;
    }
    for (int i = 0; i < n; i++) if(s[i]=='1')sum++;
    cout << sum << "\nvec: ";
    for (int i = 0; i < count.size(); i++) cout << count[i] << " "; cout << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}