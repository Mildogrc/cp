#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;
    vector<int> a(5);
    for (int i = 0; i < n; i++) {
        cin >> a[0];
        a[a[0]]++;
    }
    int sum = a[4];
    sum += a[3];
    a[1] = max(0, a[1]-a[3]);
    sum += a[2]/2;
    if (a[2]%2) {
        sum++;
        a[1] = max(0, a[1]-2);
    }
    sum += (a[1]+3)/4;

    cout << sum << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}