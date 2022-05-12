//
// @lc app=leetcode.cn id=932 lang=java
//
// [932] monotonic-array
//
class Solution {
    public boolean isMonotonic(int[] A) {
        if(A.length <= 2)
            return true;
        int i = 1;
        for(; i < A.length; ++i){
            if(A[i] != A[i-1])
                break;
        }
        if(i == A.length)
            return true;
        if(A[i] < A[i-1]){
            while(i < A.length){
                if(A[i] > A[i-1])
                    return false;
                ++i;
            }
        }else{
            while(i < A.length){
                if(A[i] < A[i-1])
                    return false;
                ++i;
            }
        }
        return true;
    }
}
// @lc code=end