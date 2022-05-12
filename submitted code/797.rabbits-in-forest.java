//
// @lc app=leetcode.cn id=797 lang=java
//
// [797] rabbits-in-forest
//
class Solution {
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }
        int res = 0;
        for (Integer key : map.keySet()) {
            Integer nums = map.get(key);
            res += (key + 1) * (nums % (key + 1) == 0 ? nums / (key + 1) : nums / (key + 1) + 1);
        }
        return res;
    }
}
// @lc code=end