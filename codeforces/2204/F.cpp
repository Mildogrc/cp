#include <bits/stdc++.h>
using namespace std;

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    long long n, m, mod;
    cin >> n >> m >> mod;
    long long sum = 0L;
    long long prev = 1L;
    long long next_prev = 0L;
    for (long long i = 0L; i < n; i++) {
        next_prev = prev * m;
        next_prev %= mod;
        sum += prev * m;
        sum %= mod;
        prev = next_prev;
    }
    cout << sum << "\n";
}