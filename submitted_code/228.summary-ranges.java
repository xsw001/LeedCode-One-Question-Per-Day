//
// @lc app=leetcode.cn id=228 lang=java
//
// [228] summary-ranges
//
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        int l = nums.length;
        int end = 0;
        int i  = 0;
        while(i < l){
            end = i;
            while(end < l-1 && nums[end+1]-nums[end]==1){
                ++end;
            }
            String s = nums[i]+"";
            if(end-i>1)
               s += ("->"+nums[end]);
            
            if(end == l-1 && nums[end]-nums[end-1]==1)
                s += ("->"+nums[end]);
            summary.add(s);
            i = end+1;
        }
        return summary;
    }
}
// @lc code=end