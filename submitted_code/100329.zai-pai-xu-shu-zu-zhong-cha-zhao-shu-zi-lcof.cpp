//
// @lc app=leetcode.cn id=100329 lang=cpp
//
// [100329] zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
//
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int len = nums.size();
        if(len == 0 || target < nums[0] || target > nums[len-1])
            return 0;
        int l = 0, r= len-1;
        while(l < r){
            int m = (l+r)/2;
            if(nums[m] >= target)
                r = m;
            else
                l = m+1;
        }
        //System.out.println(nums[l]);
        int t = l;
        l = 0; r = len-1;
        while(l < r){
            int m = (l+r+1)/2;
            if(nums[m] <= target)
                l = m;
            else
                r = m-1;
        }
        //System.out.println(nums[l]);
        if(nums[l] == target && nums[t] == target)
            return l - t + 1;
        if(l == t || nums[t] == target || nums[l] == target)
            return 1;
        return 0;
    }
};
// @lc code=end