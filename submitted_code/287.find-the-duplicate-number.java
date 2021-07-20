//
// @lc app=leetcode.cn id=287 lang=java
//
// [287] find-the-duplicate-number
//
class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length-1;
        int l = 1, r = n;
        while(l < r){
            int mid = l+r>>1;
            int cnt = 0;
            for(int num : nums)
                if(num <= mid && num >= l) ++cnt;
            if(cnt > mid - l + 1)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
// @lc code=end