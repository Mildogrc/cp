#include<bits/stdc++.h>
using namespace std;

int main() {
    int t;
    cin >> t;
    while (t-->0) {
        int n, m, l;
        cin >> n >> m >> l;
        vector<int> a(n);
        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }
        int avgstack = 0;
        int mod = 0;
        int numtoeven = 0;
        for (int i = 0; i < n; i++) {
            if (numtoeven < a[i]) {
                int extra = a[i] - numtoeven;
                mod += extra % m;
                avgstack += extra / m + mod / m;
                mod %= m;
            }
            

        }
    }
}