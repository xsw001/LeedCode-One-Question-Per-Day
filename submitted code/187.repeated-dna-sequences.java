//
// @lc app=leetcode.cn id=187 lang=java
//
// [187] repeated-dna-sequences
//
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<String>();
        HashSet<String> set = new HashSet<>();
        int l = 0, r = 0;
        while (r <= s.length()) {
            if (r - l < 10)
                ++r;
            else {
                String sub = s.substring(l, r);
                if(!set.add(sub) && !ans.contains(sub))
                    ans.add(sub);
                ++l;
            }
        }
        return ans;
    }
}
// @lc code=end