#include <bits/stdc++.h>
using namespace std;
#ifndef DEBUG
#define DEBUG 0
#endif
#define mp make_pair
#define pb push_back
#define V vector
#define vi V<int>
#define ifD if constexpr (DEBUG)
#define FOR(i, a, b) for (int i = (a); i < (b); ++i)
#define F0R(i, a) FOR(i, 0, a)
#define rep(a) F0R(_, a)
#define ROF(i, a, b) for (int i = (b) - 1; i >= (a); --i)
#define R0F(i, a) ROF(i, 0, a)
#define each(a, x) for (auto &a : x)
#define bg(x) begin(x)
#define all(x) bg(x), end(x)
#define rall(x) rbegin(x), rend(x)
#define ft front
#define bk back
#if DEBUG
#define dbg(x) cerr << #x << " = " << (x) << '\n'
#define dbgs(x) cerr << x << '\n'
#else
#define dbg(x)
#define dbgs(x)
#endif
template<typename T> using minheap = priority_queue<T, vector<T>, greater<T>>;
template<typename T> using maxheap = priority_queue<T>;
template<class T> auto poptop(T& x){auto v=x.top();x.pop();return v;}
template<class T> auto popq(T& x){auto v=x.front();x.pop();return v;}
template<class T> auto popft(T& x){auto v=x.front();x.pop_front();return v;}
template<class T> auto popbk(T& x){auto v=x.back();x.pop_back();return v;}
constexpr int pct(int x) { return __builtin_popcount(x); }
constexpr int bits(int x) { return x == 0 ? 0 : 31 - __builtin_clz(x); }
#define int long long
// #define double long double

int isPrime(int x) {
    if (x == 1) return 0;
    for(int i = 2; i*i <= x; i++) if(x%i==0) return 0;
    return 1;
}


int counter(vi &a, int j) {
    int l = j - 1, r = j + 1;
    ifD cout << "here1" << endl;
    while (l >= 0 && a[l] == 1) l--;
    ifD cout << "here2" << endl;
    while (r < a.size() && a[r] == 1) r++;
    ifD cout << "here3" << endl;
    return (j-l)*(r-j)-1;
}

void solve() {
    int n, m;
    cin >> n >> m;
    vi a(n); F0R(i, n) cin >> a[i];
    
    V<vi> mb(m);
    F0R(i, n) mb[i%m].pb(a[i]);
    
    ifD each(arr, mb) {
        each(j, arr) cout << j<< " ";
        cout << endl;
    }

    int sum = 0;
    each(arr, mb) {
        F0R(j, arr.size()) {
            if (isPrime(arr[j])) sum += counter(arr, j);
            ifD cout << sum << endl;
        }
    }
    cout << sum << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}