#include <bits/stdc++.h>
using namespace std;

#define ll long long

void solve() {
    ll n;
    cin >> n;
    vector<ll> a(n);
    for (ll i = 0; i < n; i++) cin >> a[i];
    
    vector<ll> dp(n + 1);
    stack<ll> st;
    
    for(ll i = n-1; i >= 0; i--) {
        while(!st.empty() && a[i] + 1 == a[st.top()]) 
            st.pop();
        ll r = st.empty() ? n : st.top();
        dp[i] = n-i+dp[r];
        st.push(i);
    }
    ll sum = 0;
    for(ll i = 0; i < n; i++) sum += dp[i];
    cout << sum << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    ll t; cin >> t; while (t--) solve();
}