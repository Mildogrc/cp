#include <bits/stdc++.h>
using namespace std;
// #define int long long
#define MOD 1000000000L

string big_string(vector<int> &big) {
    string x = to_string(big[big.size()-1]);
    for (int i = big.size()-2; i>=0; i--) {
        string add = to_string(big[i]);
        for (int j = 0; j < 9-add.length(); j++) x += "0";
        x += add;
    }
    return x;
}

vector<int> add(vector<int> &a, vector<int> &b) {
    vector<int> c;
    int i = 0;
    long long carry = 0;
    while (i < a.size() && i < b.size()) {
        carry += a[i];
        carry += b[i];
        int add = carry%MOD;
        c.push_back(add);
        carry /= MOD;
        i++;
    }
    if (i == a.size() && i == b.size()) {
        if (carry > 0) c.push_back(carry);
    } else {
        if (i == a.size()) {
            while (i < b.size()) {
                carry += b[i];
                int add = carry%MOD;
                c.push_back(add);
                carry /= MOD;
                i++;
            }
        } else {
            while (i < a.size()) {
                carry += a[i];
                int add = carry%MOD;
                c.push_back(add);
                carry /= MOD;
                i++;
            }
        }
    }
    return c;
}

vector<int> string_to_vec(string &x) {
    vector<int> intrep;
    int r = x.length();
    int l = r - 9;
    int carry = 0;

    while (r > 0) {
        long long num = stoll(x.substr(max(l, 0), r-max(l, 0))) + carry;
        int add = num%MOD;
        carry = num/MOD;
        intrep.push_back(add);
        r = l;
        l -= 9;
    }
    if (carry > 0) intrep.push_back(carry);
    return intrep;
}

void solve() {
    int n;
    cin >> n;
    vector<vector<int>> numbers(n);
    for (int i = 0; i < n; i++) {
        string x;
        cin >> x;
        numbers[i] = string_to_vec(x);
    }
    // for (int i = 0; i < n; i++) {
    //     for (int j = 0; j < numbers[i].size(); j++) cout << numbers[i][j] << " "; cout << "\n";
    // //     cout << big_string(numbers[i]) << endl;
    // }

    int q;
    cin >> q;
    while(q--) {
        int n;
        cin >> n;
        int a;
        cin >> a;
        vector<int> sum = numbers[a-1];
        for (int i = 1; i < n; i++) {
            cin >> a;
            sum = add(sum, numbers[a-1]);
        }
        cout << big_string(sum) << "\n";
    }
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    solve();
}