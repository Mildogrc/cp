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
    int n;
    cin >> n;
    vi a(n); F0R(i, n) cin >> a[i];
    int o = 0, sum = 0, sumcheck = 0;
    F0R (i, n) sumcheck += a[i];
    F0R (i, n) if (a[i] == 1) o = 1;
    F0R (i, n) if (a[i] != 1) sum += a[i];
    if (sumcheck == n) {
        cout << "1\n";
    } else {
        if (a[n-1] == 1) {
            cout << (sum + o) << endl;
        } else {
            cout << sum << endl;
        }
    }
    
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}