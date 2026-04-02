#include <iostream>
#include <vector>

using namespace std;

int singlePush(vector<int> &segtree, int idx, int currL, int currR) {
    int i = 1;
    while(!(currL == idx && currR == idx)) {
        // cout << "singlePush: " << i << " " << currL << " " << currR << endl;
        int mid = (currL + currR) / 2;
        segtree[2 * i] += segtree[i];
        segtree[2 * i + 1] += segtree[i];
        segtree[i] = 0;
        if (idx <= mid) {
            currR = mid;
            i = 2 * i;
        } else {
            currL = mid + 1;
            i = 2 * i + 1;
        }
    }
    // cout << "singlePush: " << i << " " << currL << " " << currR << endl;
    int ret = segtree[i];
    segtree[i] = 0;
    return ret;
}

void lazyPush(vector<int> &segtree, int l, int r, int plus, int currL, int currR, int idx = 1) {
    // cout << l << r << currL << currR << endl;
    if (currR < l || currL > r) return;
    if (l <= currL && currR <= r) {
        segtree[idx] += plus;
        return;
    }
    int mid = (currL + currR) / 2;
    segtree[2 * idx] += segtree[idx];
    segtree[2 * idx + 1] += segtree[idx];
    lazyPush(segtree, l, r, plus, currL, mid, 2 * idx);
    lazyPush(segtree, l, r, plus, mid + 1, currR, 2 * idx + 1);
    segtree[idx] = 0;
}

int sum(int x) {
    int ans = 0;
    while (x > 0) {
        ans += x % 10;
        x /= 10;
    }
    return ans;
}

int mid(int n) {
    int m = 1;
    while (m < n) m *= 2;
    return m;
}

int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        int q;
        cin >> n >> q;
        vector<int> arr;
        vector<int> done;
        int m = mid(n);
        // cout << "m: " << m << endl;
        for (int i = 0; i < n; i++) {
            int x;
            cin >> x;
            arr.push_back(x);
            done.push_back(x == sum(x));
        }
        vector<int> segtree(4*n + 5);
        for (int i = 0; i < q; i++) {
            int type;
            cin >> type;
            if (type == 1) {
                int l, r;
                cin >> l >> r;
                lazyPush(segtree, l, r, 1, 1, m);
            } else {
                int idx;
                cin >> idx;
                if (!done[idx - 1]) {
                    int times = singlePush(segtree, idx, 1, m);
                    // cout << "times: " << times << endl;
                    for (int j = 0; j < times; j++) {
                        int curr = arr[idx - 1];
                        arr[idx - 1] = sum(curr);
                        if (curr == arr[idx - 1]) {
                            done[idx - 1] = 1;
                            break;
                        }
                    }
                }
                // cout << "ans: ";
                cout << arr[idx - 1] << "\n";
            }
            // cout << "segtree: ";
            // for (int x = 0; x < segtree.size(); x++) {
            //     cout << segtree[x] << " ";
            // }
            // cout << "\n";
        }
    }
    return 0;
}
