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
// #define int long long
// #define double long double

int solve() {
    int n, m;
    cin >> n;
    vi a(n), b(n);
    F0R(i, n) cin >> a[i]; F0R(i, n) cin >> b[i];
    cin >> m; vi x(m); F0R(i, m) cin >> x[i];

    map<int, int> c;
    F0R(i, m) c[x[i]]++;

    stack<int> s; s.push(INT_MAX);

    F0R(i, n){
        if (b[i] > a[i]) return false;
        while (s.top() < b[i]) s.pop();
        if (s.top() > b[i] && a[i] > b[i]) {
            if(c[b[i]]-- <= 0) return false;
            s.push(b[i]);
        }
    }
    return true;
}

void printYN(int i) {
    if (i) cout << "YES\n";
    else cout << "NO\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) printYN(solve());
}