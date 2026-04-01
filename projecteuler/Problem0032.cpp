#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
#define int long long

int vec_count(int a, int b = 0, int c = 0) {
    vector<int> count(10);
    while (a > 0) {
        ++count[a%10];
        a/=10;
    }
    while (b > 0) {
        ++count[b%10];
        b/=10;
    }
    while (c > 0) {
        ++count[c%10];
        c/=10;
    }
    if (count[0] != 0) return 0;
    for (int i = 1; i < 10; i++) if (count[i] != 1) return 0;
    return 1;
}

void solve() {
    unordered_set<int> s;
    for (int i = 1; i <= 7852; i++) {
        for (int j = 2; j * j <= i; j++) {
            if (i%j == 0 && vec_count(i, j, i/j)) {
                cout << i << " " << j << " " << i/j << endl;
                s.insert(i);
            }
        }
    }
    int sum = 0;
    for (const auto& x : s) sum += x;
    cout << sum << endl;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}