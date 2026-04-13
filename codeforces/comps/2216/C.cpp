#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long

void solve() {
    int n, k, p, q, sum1 = 0, sum2 = 0;
    cin >> n >> k >> p >> q;
    vector<int> a(n), pq(n), qp(n), minpq(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < n; i++) pq[i] = (a[i]%p)%q;
    for (int i = 0; i < n; i++) qp[i] = (a[i]%q)%p;
    for (int i = 0; i < n; i++) minpq[i] = min(pq[i], qp[i]);

    int minsum = 0, pqsum = 0, qpsum = 0, minpqsum = 0;
    for (int i = 0; i < n; i++) minsum += minpq[i];
    for (int i = 0; i < k; i++) {
        pqsum += pq[i];
        qpsum += qp[i];
        minpqsum += minpq[i];
    }
    int mn = min(pqsum, qpsum) + minsum - minpqsum;

    for (int i = k; i < n; i++) {
        pqsum = pqsum + pq[i] - pq[i-k];
        qpsum = qpsum + qp[i] - qp[i-k];
        minpqsum = minpqsum + minpq[i] - minpq[i-k];
        mn = min(min(pqsum, qpsum) + minsum - minpqsum, mn);
    }
    cout << mn << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}