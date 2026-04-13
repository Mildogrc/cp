#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

int cycle_len(int x) {
    int l = 1, p = 1;
    map<int, int> s;
    while (true) {
        while (l < x) l *= 10;
        // cout << l << " " << p << " " << s[l] << endl;
        if (s[l]) return p - s[l];
        s[l] = p++;
        l %= x;
        if (!l) return 0;
    }
}

signed main() {
    int mx = 1;
    int v = 0;
    for (int i = 1; i < 1000; i++) {
        int newV = cycle_len(i);
        if (newV > v) {
            v = newV;
            mx = i;
        }
    }
    cout << mx << endl << v << endl;
}