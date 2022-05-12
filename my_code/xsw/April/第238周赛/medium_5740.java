package xsw.April.第238周赛;
/*
5740. 所有元音按顺序排布的最长子字符串 显示英文描述
通过的用户数18
尝试过的用户数34
用户总通过次数18
用户总提交次数41
题目难度Medium
当一个字符串满足如下条件时，我们称它是 美丽的 ：

所有 5 个英文元音字母（'a' ，'e' ，'i' ，'o' ，'u'）都必须 至少 出现一次。
这些元音字母的顺序都必须按照 字典序 升序排布（也就是说所有的 'a' 都在 'e' 前面，所有的 'e' 都在 'i' 前面，以此类推）
比方说，字符串 "aeiou" 和 "aaaaaaeiiiioou" 都是 美丽的 ，但是 "uaeio" ，"aeoiu" 和 "aaaeeeooo" 不是美丽的 。

给你一个只包含英文元音字母的字符串 word ，请你返回 word 中 最长美丽子字符串的长度 。如果不存在这样的子字符串，请返回 0 。

子字符串 是字符串中一个连续的字符序列。



示例 1：

输入：word = "aeiaaioaaaaeiiiiouuuooaauuaeiu"
输出：13
解释：最长子字符串是 "aaaaeiiiiouuu" ，长度为 13 。
示例 2：

输入：word = "aeeeiiiioooauuuaeiou"
输出：5
解释：最长子字符串是 "aeiou" ，长度为 5 。
示例 3：

输入：word = "a"
输出：0
解释：没有美丽子字符串，所以返回 0 。


提示：

1 <= word.length <= 5 * 105
word 只包含字符 'a'，'e'，'i'，'o' 和 'u'
 */

public class medium_5740 {

    public static int longestBeautifulSubstring1(String word) {
        word += "A";
        if (word.length() < 5)
            return 0;
        Character[] five = {'a', 'e', 'i', 'o', 'u'};
        int index = 0;
        int res = 0, pre = -1;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == five[index]) {
                ++index;
                if (index > 4)
                    index = 4;
            } else if (word.charAt(i) > five[index]) {
                pre = i;
                index = 0;
                continue;
            }
            if (i < word.length() - 1 && word.charAt(i) > word.charAt(i + 1)) {
                if (word.charAt(i) == 'u') {
                    res = Math.max(i - pre, res);
                }
                pre = i;
                index = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String word = "aeiou";
        System.out.println(longestBeautifulSubstring(word));
    }

    public static int longestBeautifulSubstring(String word) {
        int inf = 500000;
        int A = -inf, B = -inf, C = -inf, D = -inf, E = -inf, ans = 0;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (c == 'a') {
                A = Math.max(A + 1, 1);
                B = C = D = E = -inf;
            } else if (c == 'e') {
                B = Math.max(A + 1, B + 1);
                A = C = D = E = -inf;
            } else if (c == 'i') {
                C = Math.max(B + 1, C + 1);
                A = B = D = E = -inf;
            } else if (c == 'o') {
                D = Math.max(D + 1, C + 1);
                A = B = C = E = -inf;
            } else if (c == 'u') {
                E = Math.max(E + 1, D + 1);
                A = B = C = D = -inf;
            }
            ans = Math.max(ans, E);
        }
        return ans;
    }
}