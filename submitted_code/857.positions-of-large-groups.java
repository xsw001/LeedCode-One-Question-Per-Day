//
// @lc app=leetcode.cn id=857 lang=java
//
// [857] positions-of-large-groups
//
class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();
        int start = 0, end = 0;
        int l = s.length();
        for (int i = 0; i < l - 1;) {
            start = i;
            end = i + 1;
            while (end < l && s.charAt(end) == s.charAt(start))
                ++end;
            if(end - start >= 3){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(start);
                list.add(end-1);
                result.add(list);
            }
            i = end;
        }
        return result;
    }
}
// @lc code=end