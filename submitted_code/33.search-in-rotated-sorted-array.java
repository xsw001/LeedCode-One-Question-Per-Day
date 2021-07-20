//
// @lc app=leetcode.cn id=33 lang=java
//
// [33] search-in-rotated-sorted-array
//
class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 1)
            return nums[0] == target ? 0 : -1;
        int l = 0, r = nums.length-1;
        if(target > nums[0]){
            while(l<=r){
                int mid = l+r >> 1;
                if(nums[mid] == target)
                    return mid;
                if(nums[mid] > target)
                    r = mid-1;
                else{
                    if(nums[mid] >= nums[0])
                        l = mid+1;
                    else
                        r = mid - 1;
                }
            }
        }else{
            while(l<=r){
                int mid = l+r >> 1;
                if(nums[mid] == target)
                    return mid;
                if(nums[mid] < target)
                    l = mid + 1;
                else{
                    if(nums[mid] >= nums[0])
                        l = mid + 1;
                    else
                        r = mid-1;
                }
            }
        }
        return nums[0] == target ? 0 : -1;
    }
}
// @lc code=end