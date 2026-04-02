#include <bits/stdc++.h>
using namespace std;

int solve() {
    int n, k, p, m;
    cin >> n >> k >> p >> m;
    p--;
    queue<int> q;
    for(int i = 0; i < n; i++) {
        int card;
        cin >> card;
        q.push(card);
    }
    priority_queue<int, vector<int>, greater<int>> pq;
    int c = 0;
    while (m > 0) {
        if (p == 0) {
            if (m < q.front()) return c;
            m-= q.front();
            c++;
            p = q.size() - 1;
            q.push(q.front());
            q.pop();
        } else {
            pq.push(q.front());
            q.pop();
            while (pq.size() > k - 1) {
                if (m < pq.top()) return c;
                m -= pq.top();
                q.push(pq.top());
                pq.pop();
            }
            --p;
        }
    }
    return c;
}

signed main() {
    ios::sync_with_stdio(0); cin.tie(0);
    int t; cin >> t; while (t--) cout << solve() << "\n";
}