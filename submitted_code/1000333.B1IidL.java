//
// @lc app=leetcode.cn id=1000333 lang=java
//
// [1000333] B1IidL
//
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        while(l < r){
            int mid = (l + r) / 2 + 1;
            if(arr[mid] > arr[mid - 1])
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }
}
// @lc code=end