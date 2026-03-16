#include <bits/stdc++.h>
using namespace std;

int sum_of_digits(int s) {
    int sum = 0;
    while (s > 0) {
        sum += s%10;
        s /= 10;
    }
    return sum;
}

void dump(int x, vector<int> &mp) {
    while(x > 0) {
        mp[x%10]++;
        x/=10;
    }
}

void solve() {
    string s;
    cin >> s;
    int n = 0;
    while (s[n] != '\0') n++;
    if (n == 1) {
        cout << s << "\n";
        return;
    }
    vector<int> mp(10);
    int sum = 0;
    for(int i = 0; i < n; i++) {
        mp[s[i]-'0']++;
        sum += s[i] - '0';
    }
    int ans;

    for (int mid = 0; mid < 900000; mid++) {
        int flag = 0;
        vector<int> mid_count(10);
        int x = mid;
        while(1) {
            dump(x, mid_count);
            int p = sum_of_digits(x);
            if (x == p) break;
            x = p;
        }
        // if (mid == 12) {
        //     cout << "mp: "; for(int i = 0; i < 10; i++) cout << mp[i] << " "; cout << endl;
        //     cout << "mc: "; for(int i = 0; i < 10; i++) cout << mid_count[i] << " "; cout << endl;
        // }
        for (int i = 0; i < 10; i++) {
            if(mid_count[i] > mp[i]) flag = 1;
        }
        // if (mid == 12)
        // cout << "flag: " << flag << endl;
        if (flag) continue;
        int sum_check = 0;
        for(int i = 0; i < 10; i++) sum_check += i*(mp[i]-mid_count[i]);
        // if (mid == 12) cout << "sum_check : " << sum_check << endl;
        if (sum_check == mid) {
            // cout << "mid " << mid << endl;
            for(int i = 9; i >= 0; i--) for (int j = 0; j < (mp[i] - mid_count[i]); j++) cout << i;
            int x = mid;
            while (1) {
                cout << x;
                int p = sum_of_digits(x);
                if (x == p) break;
                x = p;
            }
            cout << "\n";
            break;
        }
    }

}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}