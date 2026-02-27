import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class E {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            List<int[]> adj = new ArrayList<>();
            adj.add(new int[]{1});
            for (int i = 0; i < n; i++) {
                String[] s = br.readLine().trim().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                if (a == 0 && b == 0) {
                    adj.add(new int[]{});
                } else {
                    adj.add(new int[]{a, b});
                }
            }
            long[] res = solve(adj);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < res.length; i++) {
                if (i > 0) sb.append(' ');
                sb.append(res[i]);
            }
            out.append(sb).append('\n');
        }
        System.out.print(out);
    }

    public static int subtreefill(List<int[]> adj, int u, int[] lsubtreesz, int[] rsubtreesz) {
        if (adj.get(u).length == 0) {
            return 1;
        }
        lsubtreesz[u] = subtreefill(adj, adj.get(u)[0], lsubtreesz, rsubtreesz);
        rsubtreesz[u] = subtreefill(adj, adj.get(u)[1], lsubtreesz, rsubtreesz);
        return lsubtreesz[u] + rsubtreesz[u] + 1;
    }

    public static long psubtreeszL(int u, long[] psubtreesz, int[] lsubtreesz, int[] rsubtreesz, int[] p) {
        if (u < 0) {
            return 0;
        }
        if (psubtreesz[u] != -1) {
            return psubtreesz[u];
        }
        long sbsz = lsubtreesz[u] * 2L + rsubtreesz[u] * 2L + 1 + psubtreeszL(p[u], psubtreesz, lsubtreesz, rsubtreesz, p);
        sbsz = sbsz % MOD;
        psubtreesz[u] = sbsz;
        return sbsz;
    }

    public static long[] solve(List<int[]> adj) {
        int n = adj.size();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = -1;
        for (int i = 0; i < n; i++) {
            for (int j : adj.get(i)) {
                p[j] = i;
            }
        }
        int[] lsubtreesz = new int[n];
        int[] rsubtreesz = new int[n];
        long[] psubtreesz = new long[n];
        for (int i = 0; i < n; i++) psubtreesz[i] = -1;
        subtreefill(adj, 1, lsubtreesz, rsubtreesz);
        psubtreesz[0] = 0;

        long[] t = new long[n - 1];
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            // long sum = lsubtreesz[j] * 2L + rsubtreesz[j] * 2L + 1;
            long sum = psubtreeszL(j, psubtreesz, lsubtreesz, rsubtreesz, p);
            // sum = (sum + parentSum) % MOD;
            t[i] = sum;
        }
        return t;
    }
}