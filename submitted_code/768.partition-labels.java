//
// @lc app=leetcode.cn id=768 lang=java
//
// [768] partition-labels
//
class Solution {
    public static List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        int begin = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Integer.max(end, map.get(s.charAt(i)));
            if (i == end) {
                list.add(end - begin + 1);
                begin = end + 1;
            }
        }
        return list;
    }
}
// @lc code=end