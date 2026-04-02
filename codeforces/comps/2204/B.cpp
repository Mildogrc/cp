#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    stack<int> stack;
    for(int i = n - 1; i >= 0; i--) {
        while(!stack.empty() && stack.top() < a[i]) stack.pop();
        stack.push(a[i]);
    }
    // cout << "ans:";
    cout << stack.size() << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}