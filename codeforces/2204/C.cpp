#include <bits/stdc++.h>
using namespace std;
void solve() {
    long long a, b, c, m;
    cin >> a >> b >> c >> m;
    long long alice = 0L, bob = 0L, carol = 0L;
    long long abc = lcm(a,lcm(b,c));
    // cout << "lcm: " << lcm(a, b) << endl;
    alice = 6*(m/a) - 6*(m/lcm(a, b)) - 6*(m/lcm(a, c)) + 6*(m/abc);
    bob = 6*(m/b) - 6*(m/lcm(a, b)) - 6*(m/lcm(b, c)) + 6*(m/abc);
    carol = 6*(m/c) - 6*(m/lcm(a, c)) - 6*(m/lcm(b, c)) + 6*(m/abc);
    // cout << "alice: " << alice << endl;
    alice += 3*(m/lcm(a, b)) + 3*(m/lcm(a, c)) - 2*3*(m/abc);
    bob += 3*(m/lcm(a, b)) + 3*(m/lcm(b, c)) - 2*3*(m/abc);
    carol += 3*(m/lcm(a, c)) + 3*(m/lcm(b, c)) - 2*3*(m/abc);
    // cout << "alice: " << alice << endl;
    alice += 2*(m/abc);
    bob += 2*(m/abc);
    carol += 2*(m/abc);

    cout << alice << " " << bob << " " << carol << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}