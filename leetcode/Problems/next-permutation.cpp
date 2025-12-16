class Solution {
public:
    void nextPermutation(vector<int>& nums) {
        int lastInd = nums.size()-1;
        while (lastInd > 0 && nums[lastInd-1] >= nums[lastInd]) {
            lastInd--;
        }
        if (lastInd == 0) {
            reverse(nums.begin(), nums.end());
            return;
        }
        int smallestNum = lastInd;
        for (int i = lastInd + 1; i < nums.size(); i++) {
            if (nums[i] > nums[lastInd - 1] && nums[i] < nums[smallestNum]) {
                smallestNum = i;
            }
        }
        vector<int> afterNums;
        afterNums.push_back(nums[lastInd - 1]);
        for(int i = lastInd; i < nums.size(); i++) {
            if (i != smallestNum) {
                afterNums.push_back(nums[i]);
            }
        }
        nums[lastInd - 1] = nums[smallestNum];
        sort(afterNums.begin(), afterNums.end());
        for (int i = lastInd; i < nums.size(); i++) {
            nums[i] = afterNums[i - lastInd];
        }
    }
};
