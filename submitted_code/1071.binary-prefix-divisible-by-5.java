//
// @lc app=leetcode.cn id=1071 lang=java
//
// [1071] binary-prefix-divisible-by-5
//
class Solution {
    public List<Boolean> prefixesDivBy5(int[] A) {
        int l = A.length;
        ArrayList<Boolean> list = new ArrayList<>();
        int num = 0, i = 0;
        while (i < l) {
            //考虑到数组 A 可能很长，如果每次都保留 N_i 的值，则可能导致溢出
            //由于只需要知道每个 N_i 是否可以被 5 整除，因此在计算过程中只需要保留余数即可。
            num = ((num << 1) + A[i]) % 5;
            list.add(num == 0);
            ++i;
        }
        return list;
    }
}
// @lc code=end