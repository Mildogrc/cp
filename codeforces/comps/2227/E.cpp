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
    int n;
    cin >> n;
    vi a(n); F0R(i, n) cin >> a[i];
    
    vi mr(n); mr[n-1] = a[n-1];
    R0F(i, n-1) mr[i] = min(a[i], mr[i+1]);
    int sum = 0;
    R0F(i, n) sum += a[i]-mr[i];
    // cout << sum << endl;


    stack<pair<int,int>> st;
    int ans = 0;
    F0R(i, n) {
        int c = 0;
        while (!st.empty() && st.top().first >= a[i]) {
            c += st.top().second;
            st.pop();
        }
        ans = max(ans, c);
        st.push({a[i], c + 1});
    }
    cout << sum+ans << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}