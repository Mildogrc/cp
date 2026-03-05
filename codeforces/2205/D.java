import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.HashMap;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            String[] line = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(line[i]);
            }
            int[] dp = solve(arr);
            int[] dp2 = reverse(solve(reverse(arr)));
            int mx = dp[0] + dp2[0] - 1;
            for (int i = 1; i < n; i++) {
                if (isValley(arr, i)) {
                    mx = Math.max(mx, dp[i] + dp2[i] - 1);
                }
            }
            int ans = n - mx;
            System.out.println(ans);
        }
    }

    public static int[] solve(int[] arr) {
        int n = arr.length;
        TreeMap<Integer, Integer> peakNextValley = new TreeMap<>();
        Map<Integer, Integer> peakIndex = new HashMap<>();
        int[] dp = new int[n];
        dp[0] = 1;
        int lastPeak = arr[0];
        peakIndex.put(arr[0], 0);
        for (int i = 1; i < n; i++) {
            if (isPeak(arr, i)) {
                lastPeak = arr[i];
                peakIndex.put(arr[i], i);
                Map.Entry<Integer, Integer> lowestPeakToAdd = peakNextValley.higherEntry(arr[i]);
                if (lowestPeakToAdd != null) {
                    int peak = peakIndex.get(lowestPeakToAdd.getKey());
                    int valley = lowestPeakToAdd.getValue();
                    int greaterThan = bs(arr, peak, valley, arr[i]);
                    dp[i] = dp[peak] + greaterThan;
                } else {
                    dp[i] = 1;
                }
                while(peakNextValley.lowerEntry(arr[i]) != null) {
                    peakNextValley.remove(peakNextValley.lowerEntry(arr[i]).getKey());
                }
            }
            else { 
                if (isValley(arr, i)) {
                    peakNextValley.put(lastPeak, i);
                }
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp;
    }

    public static boolean isPeak(int[] arr, int i) {
        if (i == arr.length - 1) return arr[i] > arr[i - 1];
        return arr[i] > arr[i - 1] && arr[i] > arr[i + 1];
    }

    public static boolean isValley(int[] arr, int i) {
        if (i == arr.length - 1) return arr[i] < arr[i - 1];
        return arr[i] < arr[i - 1] && arr[i] < arr[i + 1];
    }

    public static int bs(int[] arr, int l, int r, int c) {
        int lhs = l;
        int rhs = r;
        while(lhs < rhs) {
            int mid = (lhs + rhs + 1) / 2;
            if (arr[mid] < c) {
                rhs = mid - 1;
            } else {
                lhs = mid;
            }
        }
        return lhs - l + 1;
    }

    public static int[] reverse(int[] arr) {
        int n = arr.length;
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            rev[i] = arr[n - i - 1];
        }
        return rev;
    }
}