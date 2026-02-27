import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class G {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();
        while (t-- > 0) {
            String[] ss = br.readLine().trim().split(" ");
            int n = Integer.parseInt(ss[0]);
            int q = Integer.parseInt(ss[1]);
            List<int[]> adj = new ArrayList<>();
            int[][] queries = new int[q][2];
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
            for (int i = 0; i < q; i++) {
                String[] s = br.readLine().trim().split(" ");
                queries[i][0] = Integer.parseInt(s[0]);
                queries[i][1] = Integer.parseInt(s[1]);
            }
            int[] res = solve(adj, queries);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < res.length; i++) {
                if (i > 0) sb.append(' ');
                sb.append(res[i]);
            }
            out.append(sb).append('\n');
        }
        System.out.print(out);
    }

    public static int subtreefill(List<int[]> adj, int u, int[] lsubtreesz, int[] rsubtreesz, int[] firstInd, List<Integer> tour) {
        firstInd[u] = tour.size();
        tour.add(u);
        if (adj.get(u).length == 0) {
            return 1;
        }
        lsubtreesz[u] = subtreefill(adj, adj.get(u)[0], lsubtreesz, rsubtreesz, firstInd, tour);
        tour.add(u);
        rsubtreesz[u] = subtreefill(adj, adj.get(u)[1], lsubtreesz, rsubtreesz, firstInd, tour);
        tour.add(u);
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
        // sbsz = sbsz % MOD;
        psubtreesz[u] = sbsz;
        return sbsz;
    }

    public static int fastLog(int n) {
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    public static int[] solve(List<int[]> adj, int[][] queries) {
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
        int[] firstInd = new int[n];
        List<Integer> tour = new ArrayList<>();

        for (int i = 0; i < n; i++) psubtreesz[i] = -1;
        subtreefill(adj, 1, lsubtreesz, rsubtreesz, firstInd, tour);
        // System.out.println(tour);
        psubtreesz[0] = 0;

        long[] t = new long[n];
        for (int i = 0; i < n; i++) {
            t[i] = psubtreeszL(i, psubtreesz, lsubtreesz, rsubtreesz, p);
        }
        int[][] binaryLift = new int[n][fastLog(n) + 1];
        for (int i = 0; i < n; i++) {
            binaryLift[i][0] = p[i];
        }
        for (int j = 1; j <= fastLog(n); j++) {
            for (int i = 0; i < n; i++) {
                if (binaryLift[i][j - 1] == -1) {
                    binaryLift[i][j] = -1;
                } else {
                    binaryLift[i][j] = binaryLift[binaryLift[i][j - 1]][j - 1];
                }
            }
        }
        // System.out.println("par");
        // for (int[] par : binaryLift) {
        //     System.out.println(Arrays.toString(par));
        // }
        
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int v = queries[i][0];
            long k = queries[i][1];
            long left = t[v] - k;
            int lca = v;
            while(t[p[lca]] > left) {
                for(int parent: binaryLift[lca]) {
                    if (parent == -1) {
                        break;
                    }
                    if (t[parent] > left) {
                        lca = parent;
                    } else {
                        break;
                    }
                }
            }
            
            int more = (int)(t[lca] - left);
            res[i] = tour.get(firstInd[lca] + more);
            // int left = t[v] - k;
            
        }
        return res;
    }
}