#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

void solve() {
    int n;
    cin >> n;

    vector<int> skip(n+1, -1);
    set<pair<int, int>> m;
    stack<pair<int, int>> s;
    int k = 2;
    s.push(mp(1, 1));
    while (true) {
        cout << "? " << k << endl;
        int q;
        cin >> q;
        if (q == 0) break;
        vector<int> path(q);
        for (int i = 0; i < q; i++) cin >> path[i];
        for (int i = 0; i < q-1; i++) m.insert(mp(path[i], path[i+1]));

        while (s.size() >= q) {
            auto x = s.top();
            skip[x.first] = k-x.second;
            s.pop();
        }
        int skip_val = skip[path[q-1]];
        if (skip_val == -1) {
            s.push(mp(path[q-1], k));
            k++;
        } else {
            k+=skip[path[q-1]];
        }
    }

    cout << "! " << m.size() << "\n";
    for (const auto &p : m) {
        cout << p.first << " " << p.second << "\n";
    }
    cout.flush();
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}