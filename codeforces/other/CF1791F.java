import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CF1791F{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-->0) {
            String[] splits = br.readLine().split(" ");
            int n = Integer.parseInt(splits[0]);
            int m = mid(n);
            int q = Integer.parseInt(splits[1]);
            int[] arr = new int[n];
            String[] stringArr = br.readLine().split(" ");
            boolean[] done = new boolean[n];
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringArr[i]);
                done[i] = (arr[i] == sum(arr[i]));
            }
            int[] segtree = new int[4 * n + 5];
            for (int i = 0; i < q; i++) {
                splits = br.readLine().split(" ");
                int type = Integer.parseInt(splits[0]);
                if (type == 1) {
                    int l = Integer.parseInt(splits[1]);
                    int r = Integer.parseInt(splits[2]);
                    lazyPush(segtree, l, r, 1, 1, m, 1);
                } else {
                    int idx = Integer.parseInt(splits[1]);
                    if (!done[idx - 1]) {
                        int times = singlePush(segtree, idx, 1, m);
                        for (int j = 0; j < times; j++) {
                            int curr = arr[idx - 1];
                            arr[idx - 1] = sum(curr);
                            if (curr == arr[idx - 1]) {
                                done[idx - 1] = true;
                                break;
                            }
                        }
                    }
                    System.out.println(arr[idx - 1]);
                }
            }
        }
    }
    
    public static int singlePush(int[] segtree, int idx, int currL, int currR) {
        int i = 1;
        while(!(currL == idx && currR == idx)) {
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
        int ret = segtree[i];
        segtree[i] = 0;
        return ret;
    }

    public static void lazyPush(int[] segtree, int l, int r, int plus, int currL, int currR, int idx) {
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

    public static int sum(int x) {
        int ans = 0;
        while (x > 0) {
            ans += x % 10;
            x /= 10;
        }
        return ans;
    }

    public static int mid(int n) {
        int m = 1;
        while (m < n) m *= 2;
        return m;
    }
}