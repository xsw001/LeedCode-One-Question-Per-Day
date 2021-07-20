//
// @lc app=leetcode.cn id=1013 lang=java
//
// [1013] fibonacci-number
//
class Solution {
    public int fib(int n) {
        if(n < 2)
            return n;
        int[] arr = new int[n];
        arr[1] = 1;
        for(int i = 2; i < n;++i){
            arr[i] = arr[i-1]+arr[i-2];
        }
        return arr[n-1]+arr[n-2];
    }
}
// @lc code=end