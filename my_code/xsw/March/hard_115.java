package xsw.March;
/*
115. 不同的子序列
给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。

字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

题目数据保证答案符合 32 位带符号整数范围。



示例 1：

输入：s = "rabbbit", t = "rabbit"
输出：3
解释：
如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
(上箭头符号 ^ 表示选取的字母)
rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
示例 2：

输入：s = "babgbag", t = "bag"
输出：5
解释：
如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
(上箭头符号 ^ 表示选取的字母)
babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^

提示：

0 <= s.length, t.length <= 1000
s 和 t 由英文字母组成
 */

public class hard_115 {
    public static int res = 0;
    public static int index = 0;
    public static StringBuilder sb;
    public static char[] arr;

    public static int numDistinct(String s, String t) {
        int tl = t.length();
        int sl = s.length();
        if (tl > sl)
            return 0;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int[][] nums = new int[sl+1][tl+1];
        for (int[] num : nums) {
            num[tl] = 1;
        }
        for (int i = sl - 1; i >= 0; i--) {
            for (int j = tl - 1; j >= 0; j--) {
                if(sArr[i] == tArr[j]){
                    nums[i][j] = nums[i+1][j+1]+nums[i+1][j];
                }else
                    nums[i][j] = nums[i+1][j];
            }
        }
        return nums[0][0];
    }

    public static void main(String[] args) {
        String s = "rabbbit", t = "rabbit";
        System.out.println(numDistinct(s, t));
    }

    public static int numDistinct1(String s, String t) {
        int res = 0;
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int slen = s.length(), tlen = t.length();
        // 记录以tChar[0]开头 - tChar[j]结尾的子串的数量
        int[] tmp = new int[tlen + 1];
        tmp[0] = 1;
        for (int i = 0; i < slen; i++) {
            // 倒叙，可以自己在纸上画一画，正序需要保存前面的状态，
            for (int j = tlen; j > 0; j--) {
                if (sChar[i] == tChar[j - 1]) tmp[j] += tmp[j - 1];
            }
        }
        return tmp[tlen];
    }
}