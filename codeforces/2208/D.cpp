#include <bits/stdc++.h>
using namespace std;

int NO(){
    cout << "NO" << "\n";
    return 0;
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

int solve() {
    int n;
    cin >> n;
    vector<vector<int>> g;
    vector<vector<int>> adj(n);
    vector<int> in(n);
    vector<string> strs;
    int break_flag = 0;
    int edge_count = 0;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        vector<int> child;
        for (int j = 0; j < n; j++) {
            if (i == j && s[j] == '0') break_flag = 1;
            if (i != j && s[j] == '1') {
                child.push_back(j);
                ++in[j];
                adj[i].push_back(j);
                adj[j].push_back(i);
            }
        }
        g.push_back(child);
        strs.push_back(s);
    }
    if (break_flag) return NO();

    queue<int> q;
    vector<pair<int, int>> edges;
    // vector<int> dist(n, -1);
    for (int i = 0; i < n; i++) {
        if (in[i] == 0) {
            q.push(i);
            // dist[i] = 0;
        }
    }
    // queue<int> q2(q);
    while(!q.empty()) {
        int u = q.front(); q.pop();
        for(int &v : g[u]) {
            if (--in[v] == 0)  {
                q.push(v);
                edges.emplace_back(u, v);
            }
            // dist[v] = max(dist[v], dist[u] + 1);
        }
    }

    for (int u = 0; u < n; u++) for (int &v : g[u]){
    //         if (dist[v] <= dist[u]) return NO();
            for(int i = 0; i < n; i++) if (strs[v][i] == '1' && strs[u][i] == '0') return NO();
    }

    
    // vector<pair<int, int>> edges;
    // while(!q2.empty()) {
    //     int u = q2.front(); q2.pop();
    //     for(int &v : g[u]) {
    //         if (dist[v] == dist[u] + 1) {
    //             edges.push_back(make_pair(u, v));
    //             q2.push(v);
    //         }
    //     }
    // }
    cout << edges.size() << "\n";
    if (edges.size() != n-1) return NO();
    vector<int> dsu(n, -1);
    for(int i = 0; i < n - 1; i++) dsu_join(dsu, edges[i].first, edges[i].second);
    for(int i = 0; i < n; i++) if(get(dsu, i) != get(dsu, 0)) return NO();
    cout << "YES\n";
    // for (int i = 0; i < n-1; i++) cout << edges[i].first << " " < edges[i].second << '\n'; 
    return 0;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) solve();
}