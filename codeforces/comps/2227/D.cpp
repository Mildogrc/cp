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

int max_pal(vi &a, int l, int r, int n) {
    // cout << l << " " << r << endl;
    if (l != r) {
        int diff = (r-l)/2;
        l += diff;
        r -= diff;
    }
    // cout << "here" << endl;
    // cout << l << " " << r << endl;
    if (a[l] != a[r]) return 1;
    // cout << "here2" << endl;
    // cout << a[l] << " " << a[r] << "-";
    while (l >= 0 && r < 2*n && a[l] == a[r]) {
        // cout << a[l] << " " << a[r] << "-";
        l--; r++;
    }
    // cout << endl;
    // cout << "final pal ind: ";
    // cout << l << " " << r << endl;
    l++; r--;
    // cout << l << " " << r << endl;
    vi c(n);
    while (l <= r) {
        c[a[l]]++;
        c[a[r]]++;
        l++;
        r--;
    }
    // F0R(i, n) cout << c[i] << " "; cout << endl;
    if (c[0] == 0) return 1;
    F0R(i, n) if (c[i] == 0) return i;
    return n;
}

void solve() {
    int n;
    cin >> n;
    vi a(2*n); F0R(i, n*2) cin >> a[i];
    
    vi l(n), r(n);
    F0R(i, n*2) r[a[i]] = i;
    R0F(i, n*2) l[a[i]] = i;
    // F0R(i, n) cout << l[i] << " "; cout << endl;
    // F0R(i, n) cout << r[i] << " "; cout << endl;
    int ll = max_pal(a, l[0], l[0], n);
    int lr = max_pal(a, l[0], r[0], n);
    int rr = max_pal(a, r[0], r[0], n);
    // cout << "ans: ";
    cout << max(max(ll, lr), rr) << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}