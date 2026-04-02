#include <bits/stdc++.h>
using namespace std;

void solve() {
    unordered_set<int> st;
    int n;
    string s;
    cin >> n;
    cin >> s;
    int x = 0;
    // st.insert(x);
    while(st.find(x) == st.end() || x < 0 || x >= n) {
        st.insert(x);
        if (s[x] == 'L') x--;
        if (s[x] == 'R') x++;
    }
    st.erase(-1);
    st.erase(n);
    // cout << "ans: ";
    cout << st.size() << "\n";
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}