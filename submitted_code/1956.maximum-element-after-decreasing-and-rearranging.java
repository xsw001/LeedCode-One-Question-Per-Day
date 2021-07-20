//
// @lc app=leetcode.cn id=1956 lang=java
//
// [1956] maximum-element-after-decreasing-and-rearranging
//
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int l = arr.length;
        Arrays.sort(arr);
        if(arr[0] != 1)
            arr[0] = 1;
        for (int i = 1; i < l; i++) {
            if(arr[i] == arr[i-1])
                continue;
            arr[i] = arr[i-1]+1;
        }
        
        return  arr[l-1];
    }
}
// @lc code=end