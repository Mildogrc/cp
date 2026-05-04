#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define pb push_back
#define V vector
#define vi V<int>
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define FOR(a) F0R(_, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
#define each(a, x) for (auto &a : x)
template<typename T> using minheap = priority_queue<T, vector<T>, greater<T>>;
template<typename T> using maxheap = priority_queue<T>;
// #define int long long
// #define double long double

int isPrime(int x) {
    for(int i = 2; i*i <= x; i++) if(x%i==0) return 0;
    return 1;
}


int counter(vi &a, int j) {

}

void solve() {
    int n, m;
    cin >> n >> m;
    vi a(n); F0R(i, n) cin >> a[i];
    
    V<vi> mb(m);
    F0R(i, n) mb[i%m].pb(a[i]);

    each(arr : mb) {
        F0R(j, each.size()) {
            if (isPrime(each[j])) counter(each, j);
        }
    }

    F0R(i, m) {
        F0R(j, mb[i].size()) cout << mb[i][j] << " ";
        cout << endl;
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}