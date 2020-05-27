class Medium2 {
    static int count;
    public int pseudoPalindromicPaths (TreeNode root) {
        count = 0;
        int[] freq = new int[10];
        rec(root, freq);
        return count;
    }
    public static void rec(TreeNode node, int[] freq){
        freq[node.val]++;
        if(node.left != null){
            int[] l = new int[freq.length];
            System.arraycopy(freq, 0, l, 0, freq.length);
            rec(node.left, l);
        } else if(node.right != null){
            int[] r = new int[freq.length];
            System.arraycopy(freq, 0, r, 0, freq.length);
            rec(node.right, r);
        } else (node.left == null && node.right == null){
            if(isPalimdrom(freq)){
                count++;
            }
        }
    }
    public static boolean isPalimdrom(int[] arr){
        int odd = 0;
        int num = 0;
        for(int i = 0; i<arr.length; i++){
            if(arr[i] != 0){
                if(arr[i] % 2 == 1)
                    odd++;
                num += arr[i];
            }
        }
        return odd <= 1;
    }
}
