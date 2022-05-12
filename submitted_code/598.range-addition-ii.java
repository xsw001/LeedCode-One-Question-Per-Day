//
// @lc app=leetcode.cn id=598 lang=java
//
// [598] range-addition-ii
//
    class Solution {
        public int maxCount(int m, int n, int[][] ops) {
            int mina = m, minb = n;
            for (int[] op : ops) {
                mina = Math.min(mina, op[0]);
                minb = Math.min(minb, op[1]);
            }
            return mina * minb;
        }
    }
// @lc code=end