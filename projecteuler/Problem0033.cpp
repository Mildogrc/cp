#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long

void solve() {
    for (int i = 10; i < 100; i++) {
        for (int j = 10; j < i; j++) {
            if (i%10 == 0 && j % 10 == 0) continue;
            int dd1 = i/10;
            int dd2 = i%10;
            int nd1 = j/10;
            int nd2 = j%10;
            int x = -1, y = -1;
            if (dd1 == nd1) {
                x = nd2;
                y = dd2;
            } else if (dd1 == nd2) {
                x = nd1;
                y = dd2;
            } else if (dd2 == nd1) {
                x = nd2;
                y = dd1;
            } else if (dd2 == nd2) {
                x = nd1;
                y = dd1;
            }
            if (x == -1 || y == -1) continue;
            if (x*i == j*y) cout << j/gcd(i,j) << "/" << i/gcd(i,j) << endl;
        }
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}