class Easy {
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> max = new ArrayList<>();
        int sum = 0;
        while(sum <= sum(nums)){
            int ind = indexOfLargest(nums);
            max.add(nums[ind]);
            sum += nums[ind];
            nums[ind] = 0;
        }
        Collections.sort(max, (a,b)->b-a);
        return max;
    }
    public static int indexOfLargest(int[] nums){
        int max = 0;
        for(int i = 1; i<nums.length; i++){
            if(nums[i]>nums[max]){
                max = i;
            }
        }
        return max;
    }
    public static int sum(int[] nums){
        int sum = 0;
        for(int i = 0; i<nums.length; i++){
            sum += nums[i];
        }
        return sum;
    }
}
