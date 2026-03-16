#include <bits/stdc++.h>
using namespace std;

bool string_check(vector<vector<int>> g, vector<string> &strs, int n) {
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            if(strs[i][j] == '1')
                for (int k = 0; k < n; k++)
                    if(strs[j][k] == '1' && strs[i][k] == '0') return true;
    return false;
 }

int get(vector<int> &dsu, int i) {
    if (dsu[i] < 0) return i;
    dsu[i] = get(dsu, dsu[i]);
    return dsu[i];
}

void dsu_join(vector<int> &dsu, int i, int j) {
    i = get(dsu, i);
    j = get(dsu, j);
    if (i == j) return;
    if (dsu[i] < dsu[j]) {
        dsu[i] += dsu[j];
        dsu[j] = i;
    } else {
        dsu[j] += dsu[i];
        dsu[i] = j;
    }
}

bool dsu_connected(vector<int> &dsu) {
    for (int i = 0; i < dsu.size(); i++) if (get(dsu, i) != get(dsu, 0)) return 0;
    return 1;
}

void solve() {
    int n;
    cin >> n;
    vector<vector<int>> g;
    vector<string> strs;
    vector<unordered_set<int>> parent(n);
    int break_check = 0;

    for(int i = 0; i < n; i++) {
        string s;
        cin >> s;
        vector<int> children;
        for(int j = 0; j < n; j++) {
            if (s[j] == '1') {
                if (i != j) {
                    children.push_back(j);
                }
            } else {
                if (i == j) {
                    break_check = 1;
                }
            }
        }
        strs.push_back(s);
        g.push_back(children);
    }

    // if(string_check(strs, n) || break_check) { cout << "NO\n"; return; }

    vector<int> in(n);
    
    for (int u = 0; u < n; u++) for (int &v : g[u]) ++in[v];
    

    queue<int> q;
    for(int i = 0; i < n; i++) if (in[i] == 0) q.push(i);

    while(!q.empty()) {
        int u = q.front(); q.pop();
        for(int &v : g[u]) {
            for(int p : parent[u]) parent[v].erase(p);
            parent[v].insert(u);
            if (--in[v] == 0) q.push(v);
        }

        // for (int i = 0; i < n; i++) {
        //     cout << i << ": "; for (int p : parent[i]) cout << p << " "; cout << "\n";
        // }
    }

    // cout << "in: "; for(int i = 0; i < n; i++) cout << in[i] << " "; cout << "\n";
    for (int i = 0; i < n; i++) if (in[i]) break_check = 1;

    vector<pair<int, int>> edges;
    for(int v = 0; v < n; v++) for (int u : parent[v]) edges.emplace_back(u, v);

    vector<int> dsu(n, -1);
    for(int i = 0; i < edges.size(); i++) dsu_join(dsu, edges[i].first, edges[i].second);

    // cout << break_check << "----\n";
    if (!dsu_connected(dsu) || string_check(g, strs, n) || break_check || edges.size() != n-1) { cout << "NO\n"; return; }

    cout << "YES\n";
    for(int i = 0; i < n-1; i++) cout << edges[i].first+1 << " " << edges[i].second+1 << "\n";

}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}