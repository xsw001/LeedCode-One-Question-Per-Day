package xsw.January;
/*
1004. 最大连续1的个数 III
给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

返回仅包含 1 的最长（连续）子数组的长度。



示例 1：

输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：
[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。
示例 2：

输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 10。


提示：

1 <= A.length <= 20000
0 <= K <= A.length
A[i] 为 0 或 1

 */

public class LeedCode1004 {

    public static int longestOnes(int[] A, int K) {
        int res = 0;
        int left = 0, right = 0;
        int freq = 0;
        while (right < A.length) {
            if (A[right] == 0) {
                freq++;
            }
            right++;
            while (freq > K) {
                if (A[left] == 0) {
                    freq--;
                }
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int K = 3;
        System.out.println(longestOnes1(A, K));
    }
    public static int longestOnes1(int[] A, int K) {
        int left = 0, right = 0;
        int freq = 0;
        while (right < A.length) {
            if (A[right] == 0) {
                freq++;
            }
            if(freq > K){
                if(A[left] == 0){
                    --freq;
                }
                ++left;
            }
            right++;
        }
        //right-left表示的是当前遍历过的能满足条件的最大窗口值（注意这个窗口内的值不一定都是有效的, 他只是记录窗口的最大值）
        //这个值只增不减，最后只需要返回这个最大值即可
        return right - left;
    }
}