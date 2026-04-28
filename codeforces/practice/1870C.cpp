#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define pb push_back
#define V vector
#define vi V<int>
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
template<typename T> using minheap = priority_queue<T, vector<T>, greater<T>>;
template<typename T> using maxheap = priority_queue<T>;
// #define int long long
// #define double long double

void solve() {
    int n, k;
    cin >> n >> k;
    vi a(n); F0R(i, n) cin >> a[i];

    map<int, int> l, r;
    F0R(i, n) l[a[i]] = i;
    R0F(i, n) r[a[i]] = n-i;

    int mn = INT_MAX;
    for (auto it = r.rbegin(); it != r.rend(); it++) {
        mn = min(mn, it->second);
        it->second = mn;
    }
    mn = INT_MAX;
    for (auto it = l.rbegin(); it != l.rend(); it++) {
        mn = min(mn, it->second);
        it->second = mn;
    }
    for (auto e : l) cout << e.first << ": " << e.second << " "; cout << endl;
    for (auto e : r) cout << e.first << ": " << e.second << " "; cout << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}