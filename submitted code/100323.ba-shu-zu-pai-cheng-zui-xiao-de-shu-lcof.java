//
// @lc app=leetcode.cn id=100323 lang=java
//
// [100323] ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
//
class Solution {
    public String minNumber(int[] nums) {
        String[] array = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i] = String.valueOf(nums[i]);
        }
        quickSortString(array,0,array.length-1);
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s);
        }
        return sb.toString();
    }

    public void quickSortString(String[] strs, int l, int r) {
        if (l >= r)
            return;
        int i = l, j = r;
        String temp = strs[i];
        while (i < j) {
            while (i < j && (strs[l] + strs[j]).compareTo(strs[j] + strs[l]) <= 0) --j;
            while (i < j && (strs[l] + strs[i]).compareTo(strs[i] + strs[l]) >= 0) ++i;
            temp = strs[i];
            strs[i] = strs[j];
            strs[j] = temp;
        }
        strs[i] = strs[l];
        strs[l] = temp;
        quickSortString(strs, l, i - 1);
        quickSortString(strs, j + 1, r);
    }
}
// @lc code=end