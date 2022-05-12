//
// @lc app=leetcode.cn id=349 lang=java
//
// [349] intersection-of-two-arrays
//
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        Set<Integer> list = new HashSet<>();
        for (int i : nums2) {
            if(set.contains(i)){
                list.add(i);
            }
        }
        Integer[] array = list.toArray(new Integer[0]);
        int[] res = new int[array.length];
        int loc = 0;
        for (Integer i : array) {
            res[loc++] = i;
        }
        return res;
    }
}
// @lc code=end