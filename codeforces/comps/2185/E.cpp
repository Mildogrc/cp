#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n, m, k;
    cin >> n >> m >> k;
    vector<int> a(n), b(m);
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < m; i++) cin >> b[i];
    string s;
    cin >> s;

    set<int> spikes, robots_alive;
    for (int i = 0; i < n; i++) robots_alive.insert(i);
    for (int i = 0; i < m; i++) spikes.insert(b[i]);

    priority_queue<pair<int, int>, vector<pair<int, int>>, less<pair<int, int>>> l_bounds;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>>    r_bounds;

    for (int i = 0; i < n; i++) {
        int l = 0, r = 0;
        if (!spikes.contains(a[i])) {
            auto lower = spikes.lower_bound(a[i]);
            if (lower == spikes.begin()) {
                l = INT_MIN;
            } else {
                lower = prev(lower);
                l = *lower - a[i];
            }
            auto upper = spikes.upper_bound(a[i]);
            if (upper == spikes.end()) {
                r = INT_MAX;
            } else {
                r = *upper - a[i];
            }
            // cout << a[i] << endl;
            // cout << *lower << ", " << *upper << endl;
        }
        // cout << a[i] << endl;
        // cout << l << ", " << r << endl;
        l_bounds.push(mp(l, i));
        r_bounds.push(mp(r, i));
    }

    int x = 0;
    for (int i = 0; i < k; i++) {
        // cout << "i: " << i << "- x: " << x << endl;
        // cout << r_bounds.top().first << endl;
        if (s[i] == 'L') x--;
        else x++;
        while (!l_bounds.empty() && l_bounds.top().first >= x) {
            // cout << l_bounds.top().first << endl;
            robots_alive.erase(l_bounds.top().second);
            l_bounds.pop();
        }

        while (!r_bounds.empty() && r_bounds.top().first <= x) {
            // cout << r_bounds.top().first << endl;
            robots_alive.erase(r_bounds.top().second);
            r_bounds.pop();
        }
        cout << robots_alive.size() << " ";
    }
    cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}