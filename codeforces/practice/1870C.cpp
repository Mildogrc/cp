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

void print_queue(auto x) {
    while (!x.empty()) cout << poptop(x) << " ";
    cout << endl;
}

int ans(int x) {
    return (x+1)*2;
}

void solve(int tc) {
    int n, k;
    cin >> n >> k;
    vi a(n); F0R(i, n) cin >> a[i];

    map<int, int> mn, mx;
    minheap<int> s1(a.begin(), a.end()), s2(s1);
    ifD cout << "s1 loop \n";
    F0R(i, n) {
        ifD print_queue(s1);
        while (!s1.empty() && s1.top() <= a[i]) mn[poptop(s1)] = i;
    }
    ifD cout << "s2 loop \n";
    R0F(i, n) {
        ifD print_queue(s2);
        while (!s2.empty() && s2.top() <= a[i]) mx[poptop(s2)] = i;
    }
    ifD { for (auto &[u, v] : mn) cout << u << "=" << v << " "; cout << endl; }
    ifD { for (auto &[u, v] : mx) cout << u << "=" << v << " "; cout << endl; }
    ifD { for (auto &[u, v] : mn) cout << u << "=" << (mx[u]-v) << " "; cout << endl;}
    F0R(i, k) {
        if (mn.contains(i+1)) {
            cout << (mx[i+1]-mn[i+1]+1)*2 << " ";
        } else cout << "0 ";
    }
    cout << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}