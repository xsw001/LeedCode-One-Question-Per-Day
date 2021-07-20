//
// @lc app=leetcode.cn id=958 lang=java
//
// [958] sort-array-by-parity-ii
//
class Solution {
    public static int[] sortArrayByParityII(int[] A) {
        int[] arr = new int[A.length];
        int i = 0, j = 1;
        for (int a : A) {
            if (a % 2 == 0) {
                arr[i] = a;
                i +=2;
            }else{
                arr[j] = a;
                j +=2;
            }
        }
        return arr;
    }
}
// @lc code=end