#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;

    vector<pair<long double, long double>> tasks;

    for (int i = 0; i < n; i++) {
        long double x, y;
        cin >> x >> y;
        tasks.emplace_back(x, 100.0L-y);
    }
    long double mx = 0.0L;
    vector<int> choice;
    for(int i = n-1; i>=0; i--) {
        long double prop = tasks[i].first + tasks[i].second * mx / 100.0L;
        if (mx < prop) {
            mx = prop;
            choice.push_back(i);
        }
    }
    // cout << "choice: "; for(int i = 0; i < choice.size(); i++) cout << choice[i] << " "; cout << "\n";
    // long double sum = 0.0L;
    // long double stamina = 1.0L;
    // while(choice.size() > 0) {
    //     int i = choice.back();
    //     sum += tasks[i].first * stamina;
    //     stamina *= tasks[i].second / 100.0L;
    //     choice.pop_back();
    // }
    cout << fixed << setprecision(15) << mx << "\n";
    // cout << fixed << setprecision(15) << sum << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}
