#include <bits/stdc++.h>
using namespace std;

string inttochar(int x) {
    if (x == 0) return "R";
    if (x == 1) return "G";
    return "B";
}

vector<int> get_order(vector<int>& x) {
    vector<int> order = {0, 1, 2};
    sort(order.begin(), order.end(), [&](int a, int b) {
        return x[a] > x[b];
    });
    return order;
}


void solve() {
    vector<int> c(3);
    for (int i = 0; i < 3; i++) cin >> c[i];
    vector<int> out;
    int n = 0;

    while(c[0] + c[1] + c[2] > 0) {
        int add = -1;
        vector<int> x = get_order(c);
        for (int i = 0; i < 3; i++) {
            if (c[x[i]] == 0) continue;
            if (n > 0) {
                if (x[i] == out[n-1]) continue;
            }
            if (n > 2) {
                if (x[i] == out[n-3]) continue;
            }
            add = x[i];
            break;
        }
        // cout << "add: " << add << "\n";
        if (add == -1) break;
        c[add]--;
        out.push_back(add);
        n++;
    }
    // for (int i = 0; i < n; i++) cout << out[i] << " "; cout << "\n";
    for (int i = 0; i < n; i++) cout << inttochar(out[i]); cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}