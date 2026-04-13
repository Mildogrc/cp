#include <bits/stdc++.h>
using namespace std;
#define mp make_pair
// #define int long long

string next_int(string x) {
    return x[x.length()-1] + x.substr(0, x.length()-1);
}

signed main() {
    int n = 1000000;
    bitset<1000001> p(3);
    for (int i = 2; i <= n; i++) {
        if (!p[i]) {
            for (int j = i<<1; j <= n; j+=i) p[j]=1;
        }
    }
    p = ~p;
    // cout << p << endl;
    int counter = 0;
    for (int i = 0; i <= n; i++) {
        // cout << i << endl;
        if (p[i]) {// && !rotate_primes.contains(i)) {
            string si = to_string(i);
            string j = next_int(si);
            int f = 1;
            while (j != si) {
                if (!p[stoi(j)]) f = 0;
                j = next_int(j);
            }
            if (f) counter++;
        }
    }
    cout << counter << endl;
}