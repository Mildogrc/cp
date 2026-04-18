#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define V vector
#define vi V<int>
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
// #define int long long

int solve() {
    int n, k;
    string a, b;
    vi ca(26), cb(26);
    cin >> n >> k >> a >> b;
    F0R(i, n) ca[a[i]-'a']++;
    F0R(i, n) cb[b[i]-'a']++;
    // F0R(i, 26) cout << ca[i] << " "; cout << endl;
    // F0R(i, 26) cout << cb[i] << " "; cout << endl;
    // cout << endl;
    F0R(i, 26) {
        if (ca[i] == cb[i]) continue;
        if (cb[i] > ca[i] - k || i == 26) return 0;
        if ((ca[i] - cb[i]) % k != 0) return 0;
        ca[i+1] += ca[i] - cb[i];
        ca[i] = cb[i];
        // F0R(i, 26) cout << ca[i] << " "; cout << endl;
        // F0R(i, 26) cout << cb[i] << " "; cout << endl;
        // cout << endl;
    }
    return 1;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) cout << (solve() ? "Yes\n" : "No\n");
}