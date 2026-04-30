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

int can_mex(vi a, int mex, int n) {
    map<int, int> m;
    F0R(i, n) m[a[i]]++;
    minheap<int> pq;
    for (const auto &e : m) {
        if (e.first < mex) {
            F0R(i, e.second-1) pq.push(e.first);
            m[e.first] = 1;
        } else {
            F0R(i, e.second) pq.push(e.first);
            m[e.first] = 0;
        }
    }
    F0R (i, mex) {
        while (!pq.empty() && (pq.top()-1)/2 < i) pq.pop();
        if (!m[i]) {
            if (pq.empty()) return 0;
            pq.pop();
        }
    }
 
    return 1;
}
 
void solve() {
    int n;
    cin >> n;
    vi a(n); F0R(i, n) cin >> a[i];
    // F0R(i, 10) cout << can_mex(a, i, n) << " "; cout << endl;
    // can_mex(a, 0, n);
    int lo = 0, hi = n+1;
    while (lo < hi) {
        int mid = (lo + hi + 1)/2;
        if (can_mex(a, mid, n)) lo = mid;
        else hi = mid - 1;
    }
    cout << lo << endl;
}
 
signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}