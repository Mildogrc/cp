#include <bits/stdc++.h>
using namespace std;
const int MOD = 1000000007;

int main() {
    int t;
    cin >> t;
    while (t-->0) {
        string s;
        cin >> s;
        int c = 0;
        int n = 0;
        
        while (s[n] != '\0') {
            int i = s[n] - '0';
            if (i != 0 && i != 1){
                c = 1;
                if (i%2 == 0) {
                    s[n] = '0';
                } else {
                    s[n] = '1';
                }
            }
            n++;
        }

        int a = 1;
        if(s[n-1] == '1') c +=1;
        for(int i = n - 2; i >= 0; i--) {
            int j = a + 2;
            j %= MOD;
            if(s[i] == '1') {
                c += j;
                c %= MOD;
            }
            a += j;
            a %= MOD;
        }

        // cout << s << "\n";
        // cout << n << "\n";
        cout << c << "\n";
    }
}