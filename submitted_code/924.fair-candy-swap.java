//
// @lc app=leetcode.cn id=924 lang=java
//
// [924] fair-candy-swap
//
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
int[] result = new int[2];
        int sumA = 0, sunB = 0;
        for (int i : A) {
            sumA += i;
        }
        for (int i : B) {
            sunB += i;
        }
        int average = (sumA + sunB) / 2;
        for (int value : A) {
            for (int i : B) {
                if (sumA - value + i == average) {
                    result[0] = value;
                    result[1] = i;
                    return result;
                }
            }
        }
        return result;
    }
}
// @lc code=end