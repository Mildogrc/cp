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
#define int long long
// #define double long double

void printpq(priority_queue<int> a) {
    while (!a.empty()) {
        cout << a.top() << " "; a.pop();
    }
    cout << endl;
}

void solve() {
    int n, m;
    cin >> n >> m;
    vi a(n), x(m);
    F0R(i, n) cin >> a[i]; F0R(i, m) cin >> x[i]; 

    maxheap<int> e, o;
    F0R(i, n) {
        if (i&1) e.push(a[i]);
        else o.push(a[i]);
    }
    // cout << "pre-o:\n";
    // printpq(o); 
    // cout << "pre-e:\n";
    // printpq(e);
    int f_o = 1, f_e = 1;
    F0R(i, m) {
        if (x[i]&1) {
            if (!o.empty()) {
                if (o.top() > 0 || f_o) {
                    o.pop();
                    f_o = 0;
                }
            }
        }
        else {
            if (!e.empty()) {
                if (e.top() > 0 || f_e) {
                    e.pop();
                    f_e = 0;
                }
            }
        }
    }

    int sum = 0;
    // cout << "o and e pq:\n";
    while (!o.empty()) {
        // cout << o.top() << " ";
        sum += o.top(); o.pop();
    }
    // cout << endl;
    while (!e.empty()) {
        // cout << e.top() << " ";
        sum += e.top(); e.pop();
    }
    // cout << endl;
    cout << sum << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}