package Tiger2022.offer.codeTop;
/*
3. 无重复字符的最长子串
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。



示例 1:

输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。


提示：

0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成
 */

import org.junit.Test;

import java.util.HashMap;

public class 无重复字符的最长子串_3 {

    @Test
    public void test() throws Exception {
        String s = "abba";
        System.out.println(lengthOfLongestSubstring(s));
    }

    // 思路错
    public int lengthOfLongestSubstring1(String s) {
        if (s.length() < 2)
            return s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = -1, pre = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                Integer a = map.get(s.charAt(i));
                if (ans == -1) {
                    ans = a + 1;
                }
                ans = Math.max(ans, i - a);
                pre = i;
            }
            map.put(s.charAt(i), i);
        }
        ans = Math.max(ans, s.length() - pre);
        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2)
            return s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = -1;
        int l = 0, r = 0;
        while (r < s.length()) {
            if (map.containsKey(s.charAt(r))) {
                ans = Math.max(ans, r - l);
                if (l < map.get(s.charAt(r)) + 1)
                    l = map.get(s.charAt(r)) + 1;
            }
            map.put(s.charAt(r), r++);
        }
        return Math.max(ans, r - l);
    }
}
