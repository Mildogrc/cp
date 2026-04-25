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

void solve() {
    int n, m;
    cin >> n >> m;
    vi a(n);
    F0R(i, n) cin >> a[i];

    maxheap<int> mpq;
    int flips = 0, sum = 0;
    if (m > 1 && a[m-1] > 0){
        flips++;
        a[m-1] *= -1;
    }
    sum += a[m-1];
    R0F(i, m-1) {
        if (a[i] > 0) mpq.push(a[i]);
        sum += a[i];
        while(sum > 0) {
            sum -= 2*mpq.top();mpq.pop();
            flips++;
        }
    }

    minheap<int> pq;
    sum = 0;
    FOR(i, m, n) {
        if (a[i] < 0) pq.push(a[i]);
        sum += a[i];
        while (sum < 0) {
            sum -= 2*pq.top();pq.pop();
            flips++;
        }
        // cout << sum << endl;
    }
    cout << flips << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}