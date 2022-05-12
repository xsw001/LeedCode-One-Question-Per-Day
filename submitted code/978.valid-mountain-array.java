//
// @lc app=leetcode.cn id=978 lang=java
//
// [978] valid-mountain-array
//
class Solution {
    public boolean validMountainArray(int[] A) {
        int len = A.length-1;
        if(len < 2)
            return false;
        int begin = 0;
        int last = len;
        while(begin < len-1 && A[begin] < A[begin + 1])
            ++begin;
        while(last > 1 && A[last] < A[last-1])
            --last;

        return last == begin;
    }
}
// @lc code=end