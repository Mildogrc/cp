#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>

using namespace std;
using namespace __gnu_pbds;

typedef
tree<
    int,
    null_type,
    less<int>,
    rb_tree_tag,
    tree_order_statistics_node_update>
treemap;

const int base = 1e9;

int main() {
    int t;
    cin >> t;
    while (t-->0) {
        int n;
        cin >> n;
        vector<int> a(n);
        vector<int> b(n);
        treemap use;
        treemap s;
        for(int i = 0; i < n; i++) {
            cin >> a[i];
            b[i] = a[i];
            s.insert(i);
            use.insert(i);
        }
        s.insert(n);
        
        for (int i = 0; i < n; i++) use.erase(a[i]);

        vector<int> out;
        int possible = 1;
        for(int i = n - 1; i >= 0; i--) {
            int curr = *s.find_by_order(i);
            if(curr == a[n - i - 1]) {
                out.push_back(base);
            } else {
                int mx = *use.find_by_order(use.size()-1);
                use.erase(mx);
                s.erase(mx);
                out.push_back(mx);
                int new_curr_check = *s.find_by_order(i);
                if(new_curr_check != a[n - i - 1]) possible = 0;
            }
        }
        if (possible) {
            cout << "YES" << "\n";
            for(int i = 0; i < out.size(); i++) cout << out[i] << " ";
            cout << "\n";
        } else {
            cout << "NO" << "\n";
        }
    }
}