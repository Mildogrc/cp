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
template<class T> auto poptop(T& x){
    if constexpr(requires{x.top();}){auto v=x.top();x.pop();return v;}
    else if constexpr(requires{x.pop();}){auto v=x.front();x.pop();return v;}
    else{auto v=x.front();x.pop_front();return v;}}
template<class T> auto pop_small(T& x){auto it=x.begin();auto v=*it;x.erase(it);return v;}
template<class T> auto pop_large(T& x){auto it=prev(x.end());auto v=*it;x.erase(it);return v;}
constexpr int pct(int x) { return __builtin_popcount(x); }
constexpr int bits(int x) { return x == 0 ? 0 : 31 - __builtin_clz(x); }
#define int long long
// #define double long double



void solve(int tc) {
    int n, k = 0;
    cin >> n;
    vi a(n); F0R(i, n) cin >> a[i];

    maxheap<int> pq;
    F0R(i, n) pq.push(a[i]);

    map<int, set<int>> ind, frontier;
    F0R(i, n) ind[a[i]].insert(i);

    vi vis(n);
    while(!pq.empty()) {
        int i = poptop(pq), x;
        if (frontier[i].empty()) {
            k++;
            int x = pop_small(ind[i]);
            vis[x] = 1;
            if (x > 0 && !vis[x-1]) frontier[a[x-1]].insert(x-1);
            if (x < n-1&&!vis[x+1]) frontier[a[x+1]].insert(x+1);
        } else {
            int x = pop_small(frontier[i]);
            ind[i].erase(x);
            vis[x] = 1;
            if (x > 0 && !vis[x-1]) frontier[a[x-1]].insert(x-1);
            if (x < n-1&&!vis[x+1]) frontier[a[x+1]].insert(x+1);
        }
    }

    cout << k << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}