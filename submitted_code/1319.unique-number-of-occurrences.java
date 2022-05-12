//
// @lc app=leetcode.cn id=1319 lang=java
//
// [1319] unique-number-of-occurrences
//
class Solution {
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : arr) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        return map.size() == new HashSet<>(map.values()).size();
    }
}
// @lc code=end