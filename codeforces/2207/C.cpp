#include<bits/stdc++.h>
using namespace std;

void build_each(vector<int> &a, vector<long long> &each, int i, int n, int h) {
    long long mx = a[i];
    long long sum = h - a[i];
    for(int j = i + 1; j < n; j++) {
        long long curr = a[j];
        mx = max(curr, mx);
        sum += h - mx;
    }
    mx = a[i];
    for(int j = i - 1; j >= 0; j--) {
        long long curr = a[j];
        mx = max(curr, mx);
        sum += h - mx;
    }
    each[i] = sum;
}

int main() {
    int t;
    cin>>t;
    while(t-->0) {
        int n, h;
        cin>>n>>h;
        vector<int> a(n);
        vector<long long> each(n);

        for (int i = 0; i < n; i++) cin>>a[i];

        for (int i = 0; i < n; i++) build_each(a, each, i, n, h);


        long long mn = each[0];
        for(int i = 0; i < n; i++) {
            int mx = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] > a[mx]) mx = j;
                long long curr = each[i] + each[j] - each[mx];
                mn = max(mn, curr);
            }
        }
        cout << mn << "\n";
    }
    return 0;
}