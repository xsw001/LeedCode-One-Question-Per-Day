//
// @lc app=leetcode.cn id=1458 lang=java
//
// [1458] sort-integers-by-the-number-of-1-bits
//
class Solution {
    public static int[] sortByBits(int[] arr) {
                for (int i = 0; i < arr.length; i++) {
            arr[i] += binary(arr[i]) * 100000;
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] %= 100000;
        }
        return arr;
    }
        public static int binary(int a) {
        int num = 0;
        while (a != 0) {
            num += a % 2;
            a = a / 2;
        }
        return num;
    }
}
// @lc code=end