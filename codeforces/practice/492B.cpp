#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n, l;
    cin >> n >> l;
    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    sort(a.begin(), a.end());

    int mn = max((a[0]-0), l-a[n-1])*2;
    for (int i = 0; i < n-1; i++) mn = max(mn, a[i+1]-a[i]);
    cout << mn/2;
    if (mn&1) cout << ".5";
    cout << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}
