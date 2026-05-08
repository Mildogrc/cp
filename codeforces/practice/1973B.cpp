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
// #define int long long
// #define double long double

vi bitify(int x) {
    vi ret(21);
    F0R(i, 21) if (x&(1<<i)) ret[i]=1;
    return ret;
}

int works(int k, vi &a, int n) {
    ifD cout << "checking: " << k << ":\n";
    vi bits(21), checkbit(21);
    F0R(i, k) {
        vi cbit = bitify(a[i]);
        ifD F0R(i, 21) cout << cbit[i] << " \n"[i==20];
        F0R(b, 21) bits[b] += cbit[b];
        F0R(b, 21) checkbit[b] += cbit[b];
    }
    ifD F0R(i, 21) cout << checkbit[i] << " \n"[i==20];
    FOR(i, k, n) {
        vi cbit = bitify(a[i]);
        F0R(b, 21) bits[b] += cbit[b];
        cbit = bitify(a[i-k]);
        F0R(b, 21) bits[b] -= cbit[b];
        ifD F0R(i, 21) cout << bits[i] << " \n"[i==20];
        F0R(b, 21) {
            if (checkbit[b] && !bits[b]) return false;
            if (!checkbit[b] && bits[b]) return false;
        }
    }
    return true;
}

void solve(int tc) {
    int n, k = 0;
    cin >> n;
    vi a(n); F0R(i, n) cin >> a[i];

    int lo = 1, hi = n;
    while (lo < hi) {
        int mid = (lo+hi)/2;
        if(!works(mid, a, n)) lo = mid+1;
        else hi = mid;
    }
    cout << lo << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}