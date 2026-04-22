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
// #define int long long
// #define double long double

int is_div(string &s, int n, int d){
    string e = s.substr(0, d);
    string x = "";
    F0R(i, (n/d)) x += e;
    // cout << s << endl;
    // cout << x << endl;
    return (s==x);
}

void solve() {
    string s1, s2;
    cin >> s1 >> s2;
    int n1 = s1.length(), n2 = s2.length();
    // map<int, string> m;
    int sum = 0;
    FOR(i, 1, (max(n1, n2)+1)) {
        if (n1%i == 0 && 
            n2%i==0 && 
            s1.substr(0, i) == s2.substr(0, i) && 
            is_div(s1, n1, i) && 
            is_div(s2, n2, i)) sum++;
    }
    cout << sum << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}