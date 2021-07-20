//
// @lc app=leetcode.cn id=100092 lang=java
//
// [100092] deep-dark-fraction
//
class Solution {
    public int[] fraction(int[] cont) {
        int len = cont.length;
        int[] res = {1, cont[len - 1]};
        for (int i = cont.length - 2; i >= 0; i--) {
            res[0] = cont[i] * res[1] + res[0];
            swap(res);
        }
        swap(res);
        return res;
    }

    public void swap(int[] a) {
        int temp = a[1];
        a[1] = a[0];
        a[0] = temp;
    }
}
// @lc code=end