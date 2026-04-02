#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long

void solve() {
    vector<int> fac(10,1);
    for (int i = 2; i < 10; i++) fac[i] = fac[i-1]*i;
    cout << endl;
    int i = 2;
    int sum = 0;
    for (int i = 3; i < fac[9]; i++) {
        int j = i;
        int curr_sum = 0;
        while (j > 0) {
            curr_sum += fac[j%10];
            j /= 10;
        }
        if (curr_sum == i) {
            sum += i;
            cout << i << " " << sum << endl;
        }
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}