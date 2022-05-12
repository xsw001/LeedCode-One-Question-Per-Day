//
// @lc app=leetcode.cn id=229 lang=java
//
// [229] majority-element-ii
//
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        int l = nums.length;
        int one = 0, two = 0;
        int v1 = 0, v2 = 0;
        for (int num : nums) {
            if (num == one) {
                ++v1;
            } else if (num == two) {
                ++v2;
            } else if (v1 == 0) {
                one = num;
                ++v1;
            } else if (v2 == 0) {
                two = num;
                ++v2;
            } else {
                --v1;
                --v2;
            }
        }
        v1 = 0;
        v2 = 0;
        for (int num : nums) {
            if (num == one) ++v1;
            else if (num == two) ++v2;
        }
        if (v1 > l / 3) list.add(one);
        if (v2 > l / 3) list.add(two);
        return list;
    }
}
// @lc code=end