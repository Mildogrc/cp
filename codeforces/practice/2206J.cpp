#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
#define int long long

void swap(int &x, int &y) {
    int t = y;
    y = x;
    x = t;
}

int update_jump(vector<int> &a, vector<int> &b, vector<int> &jump, vector<int> &m, int i, int n) {
    // cout << i << endl;
    int prev_ind = 0;
    jump[i] = 0;
    if (i > 0) prev_ind = m[b[i-1]];
    if (prev_ind > m[b[i]]) {
        jump[i] += n-prev_ind;
        prev_ind = 0;
    } 
    jump[i] += m[b[i]] - prev_ind;
    // F0R (i, n) cout << jump[i] << " "; cout << endl;
    return jump[i];
}

void solve() {
    int n, d;
    cin >> n >> d;
    vector<int> a(n), b(n), m(n), jump(n), m2(n);
    F0R(i, n) cin >> a[i]; F0R(i, n) cin >> b[i];
    F0R(i, n) a[i]--; F0R(i, n) b[i]--;
    F0R(i, n) m[a[i]] = i;
    F0R(i, n) m2[b[i]] = i;
    F0R(i, n) update_jump(a, b, jump, m, i, n);
    int sum = 0;
    F0R(i, n) sum += jump[i];
    // F0R(i, n) cout << jump[i] << " "; cout << endl;
    cout << sum - n + 1 << endl;
    F0R(_, d-1) {
        int c, x, y;
        cin >> c >> x >> y;
        x--; y--;
        if (c == 1) {
            swap(a[x], a[y]);
            m[a[x]] = x; m[a[y]] = y;
            sum -= jump[m2[a[x]]];
            sum += update_jump(a, b, jump, m, m2[a[x]], n);
            sum -= jump[m2[a[y]]];
            sum += update_jump(a, b, jump, m, m2[a[y]], n);
            sum -= jump[m2[a[x]] + 1];
            sum += update_jump(a, b, jump, m, m2[a[x]] + 1, n);
            if (m2[a[y]] != n-1) {
                sum -= jump[m2[a[y]] + 1];
                sum += update_jump(a, b, jump, m, m2[a[y]] + 1, n);
            }
        } else {
            swap(b[x], b[y]);
            sum -= jump[x];
            sum += update_jump(a, b, jump, m, x, n);
            sum -= jump[y];
            sum += update_jump(a, b, jump, m, y, n);
            sum -= jump[x + 1];
            sum += update_jump(a, b, jump, m, x + 1, n);
            if (y != n-1) {
                sum -= jump[y + 1];
                sum += update_jump(a, b, jump, m, y + 1, n);
            }
        }
        cout << sum - n + 1 << "\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}