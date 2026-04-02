#include<bits/stdc++.h>
using namespace std;

int main() {
    int t;
    cin >> t;
    while (t-->0) {
        int n;
        cin >> n;
        string s;
        cin >> s;
        for (int i = 1; i < n - 1; i++) {
            if (s[i - 1] == '1' && s[i + 1] == '1') {
                s[i] = '1';
            }
        }
        int mx = 0;
        vector<int> counts;
        int ind = -1;
        int prev = '0';
        for (int i = 0; i < n; i++) {
            if (s[i] == '1') {
                mx++;
                if (prev == '0') {
                    counts.push_back(1);
                    ind++;
                } else {
                    counts[ind]++;
                }
            }
            prev = s[i];
        }
        // cout << "s: " << s << endl;
        // cout << "vector: ";
        int mn = 0;
        for (int i = 0; i < counts.size(); i++) {
            mn += counts[i];
            mn -= (counts[i] - 1) / 2;
        //     cout << counts[i] << " ";
        }
        // cout << endl;
        // cout << "min: " << mn;
        // cout << "ans: ";
        cout << mn << " " << mx << "\n";
    }
}