#include <bits/stdc++.h>
using namespace std;
#define int long long

int f(string x, int l, int r) {
    int sum = 0;
    int c = 1;
    int j = l;
    for (int i = l; i <= r; i++) {
        if(x[i] == x[j]) {
            c++;
        } else if (x[i] )
    }
}

void solve() {
    int n, q;
    string s;
    cin >> n >> q;
    cin >> s;
    while(q--) {
        int l, r;
        cin >> l >> r;
        cout << f(s, l-1, r-1) << "\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}