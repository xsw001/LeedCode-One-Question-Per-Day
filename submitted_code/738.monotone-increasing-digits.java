//
// @lc app=leetcode.cn id=738 lang=java
//
// [738] monotone-increasing-digits
//
class Solution {
    public static int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        //如果找到第一个位置 i 使得 [0,i-1] 的数位单调递增且 strN[i−1]>strN[i]
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }//此时 [0,i] 的数位都与 N 的对应数位相等，仍然被 N 限制着
        //为了得到最大的数字，我们需要解除 N 的限制，来让剩余的低位全部变成 9 ，即能得到小于 N 的最大整数
        //故尝试让 strN[i−1] 自身数位减 1。此时已经不再受 N 的限制，直接将 [i, n-1] 的位置上的数全部变为 9
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            //当 strN[i−1] 自身数位减 1 后可能会使得 strN[i−1] 和 strN[i−2] 不再满足递增的关系
            //因此我们需要从 i-1 开始递减比较相邻数位的关系
            //直到找到第一个位置 j 使得 strN[j] 自身数位减 1 后 strN[j−1] 和 strN[j] 仍然保持递增关系
            //或者位置 j 已经到最左边（即 j 的值为 0）
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }
}
// @lc code=end