//
// @lc app=leetcode.cn id=331 lang=java
//
// [331] verify-preorder-serialization-of-a-binary-tree
//
class Solution {
    public boolean isValidSerialization(String s) {
                int l = s.length();
        int count = 0;

        for (int i = l - 1; i >= 0; i--) {
            if (s.charAt(i) == ',')
                continue;
            if (s.charAt(i) == '#')
                ++count;
            else {
                while (i>=0 && s.charAt(i) != ',')
                    --i;
                if(count < 2)
                    return false;
                --count;
            }
        }
        return count == 1;
    }
}
// @lc code=end