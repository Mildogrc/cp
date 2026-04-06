#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long


void solve() {
    int n, ax, ay, bx, by;
    cin >> n >> ax >> ay >> bx >> by;
    vector<int> x(n), y(n);
    for (int i = 0; i < n; i++) cin >> x[i];
    for (int i = 0; i < n; i++) cin >> y[i];
    x.push_back(bx);
    y.push_back(by);

    map<int, int> mx, mn;
    for (int i = 0; i <= n; i++) mx[x[i]] = y[i];
    for (int i = 0; i <= n; i++) mn[x[i]] = y[i];
    for (int i = 0; i < n; i++) mx[x[i]] = max(mx[x[i]], y[i]);
    for (int i = 0; i < n; i++) mn[x[i]] = min(mn[x[i]], y[i]);
    mx[ax] = ay;
    mn[ax] = ay;
    sort(x.begin(), x.end());

    int prevUp = 0, prevDown = 0, prevX = ax;
    for (int i = 0; i <= n; i++) {
        if (x[i] > prevX) {
            int seglength = mx[x[i]] - mn[x[i]];
            // cout << prevX << ": " << prevUp << ", " << prevDown << endl;
            // cout << x[i] << ": " << mx[x[i]] << ", " << mn[x[i]] << "= " << seglength << endl;
            int upup = prevUp+abs(mx[prevX]-mn[x[i]]) + seglength;
            int downup = prevDown+abs(mn[prevX]-mn[x[i]]) + seglength;
            int updown = prevUp+abs(mx[prevX]-mx[x[i]]) + seglength;
            int downdown = prevDown+abs(mn[prevX]-mx[x[i]]) + seglength;
            prevUp = min(upup, downup);
            prevDown = min(updown, downdown);
            prevX = x[i];
        }
    }
    // cout << prevX << ": " << prevUp << ", " << prevDown << endl;
    cout << prevUp + bx-ax << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}