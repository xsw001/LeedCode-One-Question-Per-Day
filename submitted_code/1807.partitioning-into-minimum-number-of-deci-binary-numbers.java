//
// @lc app=leetcode.cn id=1807 lang=java
//
// [1807] partitioning-into-minimum-number-of-deci-binary-numbers
//
class Solution {
    public int minPartitions(String n) {
        char[] chars = n.toCharArray();
        int ans = 0;
        for(int i=0;i<chars.length;i++){
            if(chars[i] - '0' > ans){
                ans = chars[i] - '0';
            }
        }
        return ans;
    }
}
// @lc code=end