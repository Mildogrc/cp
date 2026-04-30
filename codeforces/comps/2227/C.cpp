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
    
    vi s, d2, d3, o;

    F0R(i, n) {
        if (a[i]%6==0) {
            s.pb(a[i]);
        } else if (a[i] % 2 == 0) {
            d2.pb(a[i]);
        } else if (a[i] % 3 == 0) {
            d3.pb(a[i]);
        } else {
            o.pb(a[i]);
        }
    }
    for (int i : s) cout << i << " ";
    for (int i : d3) cout << i << " ";
    for (int i : o) cout << i << " ";
    for (int i : d2) cout << i << " ";
    cout << "\n";

}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}