#include<bits/stdc++.h>
using namespace std;


int main() {
    int t;
    cin >> t;
    while (t-->0) {
        int n, m, l;
        cin >> n >> m >> l;
        queue<int> a;
        for (int i = 0; i < n; i++) {
            int x;
            cin >> x;
            a.push(x);
        }
        multiset<int> ms;
        for(int i = 0; i < m; i++) ms.insert(0);
        
        // cout << "ms sz: " << ms.size() << ", ms: ";
        // for(int i: ms) cout << i << " ";
        // cout << endl;

        for(int i = 1; i <= l; i++) {
            while(ms.size() > a.size() + 1) ms.erase(ms.begin());
            int smallest = *ms.begin();
            ms.erase(ms.begin());
            ms.insert(smallest + 1);
            if (!a.empty() && i == a.front()) {
                a.pop();
                ms.erase(prev(ms.end()));
                ms.insert(0);
            }
            // cout << "i: " << i << ", ms: ";
            // for(int x: ms) cout << x << " ";
            // cout << endl;
        }
        // cout << "ans: ";
        cout << *prev(ms.end()) << "\n";
    }
}