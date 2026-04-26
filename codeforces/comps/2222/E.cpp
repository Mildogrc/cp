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
#define int long long
// #define double long double

void solveAND(int n) {
    int prevsz = 1, sz;
    int x = 0;
    F0R(i, n) {
        cout << "I " << (1LL<<i) << endl;
        cin >> sz;
        if (sz != prevsz) {
            x += (1LL<<i);
        }
        prevsz = sz;
    }
    cout << "A 1 " << x << endl;
}

int bsearch(int n) {
    int lo = 1, hi = (1LL<<n)-1, cnt;
    while (lo < hi) {
        int mid = (lo + hi + 1)/2;
        cout << "Q " << mid << endl;
        cin >> cnt;
        if (cnt) lo = mid;
        else hi = mid-1;
    }
    return lo;
}

void solve() {
    int n, sz;
    cin >> n;
    cout << 0 << endl;
    cout << "I 0" << endl;
    cin >> sz;
    if (sz == 1) {
        solveAND(n);
    } else {
        int x = bsearch(n), mx = (1LL<<n)-1, k = 3;
        if (x == mx) {
            cout << "I " << 1 << endl;
            cin >> sz;
            if (sz == 2) k = 2;
        } else {
            cout << "I " << mx << endl;
            cin >> sz;
            cout << "Q " << mx << endl;
            cin >> sz;
            if (sz) k = 2;
        }
        cout << "A " << k << " " << x << endl;
    }

}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}