#include <bits/stdc++.h>
using namespace std;
#define int long long

vector<int> create_primes() {
    vector<int> sieve(32000, 1);
    vector<int> primes;
    sieve[0] = 0;
    sieve[1] = 0;
    for (int i = 2; i < 32000; i++) {
        if (sieve[i]) {
            for (int j = i * 2; j < 32000; j+=i) sieve[j] = 0;
            primes.push_back(i);
        }
    }
    return primes;
}

void solve(vector<int> &primes) {
    int n;
    cin >> n;
    vector<int> a(n), b(n);
    for (int i = 0; i < n; i++) cin >> a[i]; 
    for (int i = 0; i < n; i++) cin >> b[i];
    
    vector<int> prev(primes.size()+1);

    int g = gcd(a[0], a[1]);
    if (g != a[0]) prev[0] = 1;
    for (int i = 0; i < primes.size(); i++) {
        int t = g * primes[i];
        if (t != a[0] && t < b[0]) prev[i+1]++;
    }

    for (int i = 1; i < n - 1; i++) {
        int l = gcd(a[i - 1], a[i]);
        int r = gcd(a[i], a[i + 1]);
        int t = lcm(l, r);
        int 
    }
}

signed main() {
    vector<int> primes = create_primes();
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve(primes);
}