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

int get(vi &dsu, int x) {
    if (dsu[x] < 0) return x;
    dsu[x] = get(dsu, dsu[x]);
    return dsu[x];
}

int combine(vi &dsu, int i, int j) {
    i = get(dsu, i);
    j = get(dsu, j);
    if (dsu[i] < dsu[j]) {
        dsu[i] += dsu[j];
        dsu[j] = i;
        return j;
    } else {
        dsu[j] += dsu[i];
        dsu[i] = j;
        return i;
    }
}

void run(int i, int j, auto &edges, auto &nodes, auto &dsu) {
    if (get(dsu, i) != get(dsu, j)) {
        cout << "? " << i << " " << j << endl;
        int mid; cin >> mid;
        if (mid == i || mid == j) {
            edges.insert(mp(min(i, j), max(i, j)));
            nodes.erase(combine(dsu, i, j));
        } else {
            run(i, mid, edges, nodes, dsu);
            run(mid, j, edges, nodes, dsu);
        }
    }
}

void solve(int tc) {
    int n;
    cin >> n;

    set<pair<int, int>> edges;
    set<int> nodes; F0R(i, n) nodes.insert(i+1);
    vi dsu(n+1, -1);

    while(nodes.size() > 1) {
        int i = *nodes.begin(), j = *prev(nodes.end());
        run(i, j, edges, nodes, dsu);
    }

    cout << "! ";
    for(auto &[u, v] : edges) cout << u << " " << v << " "; cout << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; rep(t) solve(_+1);
}