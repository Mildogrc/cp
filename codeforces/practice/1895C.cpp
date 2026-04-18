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
#define int long long

pair<int, int> get_key(int i) {
    int l = to_string(i).length();
    int sum = 0;
    while (i > 0) {
        sum += i%10;
        i/=10;
    }
    return mp(sum, l);
}

int c2(int x) {
    return (x * (x-1));
}

void solve() {
    int n;
    cin >> n;
    vi a(n);
    F0R(i, n) cin >> a[i];

    map<pair<int, int>, int> m;

    F0R (i, n) m[get_key(a[i])]++;

    int sum = 0;
    F0R(i, n) {
        string x = to_string(a[i]);
        auto e = get_key(a[i]);
        int l = 0, r = e.first;
        int ll = 0, rl = x.length();
        set<pair<int,int>> s;
        sum += m[mp(r, rl)];
        s.insert(mp(r, rl));
        F0R(i, e.second) {
            r -= (x[i]-'0');
            l += (x[i]-'0');
            ll++;
            rl--;
            if (ll < rl) {
                sum += m[mp(r-l, rl-ll)];
                s.insert(mp(r-l, rl-ll));
                s.insert(mp(rl-ll, r-l));
            } else {
                sum += m[mp(l-r, ll-rl)];
                s.insert(mp(l-r, ll-rl));
                s.insert(mp(ll-rl, l-r));
            }
        }
        sum--;
    }
    for (const auto &e : m) sum -= c2(e.second);
    cout << sum << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}