#include<bits/stdc++.h>

using namespace std;

int main() {
    int t;
    cin >> t;
    while (t-->0) {
        int n;
        cin >> n;
        string s;
        // getline(cin, ones);
        cin >> s;
        for (int i = 1; i < n - 1; i++) {
            if (s[i - 1] == '1' && s[i + 1] == '1') {
                s[i] = '1';
            }
        }

        vector<int> counts;
        int ind = -1;
        int zero = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == '1') {
                if (zero) {
                    zero = 1;
                    counts.add(1);
                    ind++;
                } else {
                    
                }
            }
        }
        cout << "s: " << s << endl;
        cout << s[0] << endl;
    }
}