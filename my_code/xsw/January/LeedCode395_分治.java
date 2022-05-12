package xsw.January;
/*
395. 至少有 K 个重复字符的最长子串
给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。



示例 1：

输入：s = "aaabb", k = 3
输出：3
解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
示例 2：

输入：s = "ababbc", k = 2
输出：5
解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。


提示：

1 <= s.length <= 104
s 仅由小写英文字母组成
1 <= k <= 105
 */

import java.util.HashMap;
import java.util.Map;

public class LeedCode395_分治 {

    //思路错误
    public static int longestSubstring(String s, int k) {
        int l = s.length();
        if (k > l) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < l; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int result = 0;
        int left = 0, right = 0;
        while (right < l) {
            if (map.get(s.charAt(right)) < k) {
                result = Math.max(result, right - left);
                ++right;
                left = right;
            } else
                ++right;
        }
        return Math.max(result, right - left);
    }

    public static void main(String[] args) {
        System.out.println(longestSubstring1("abasbabbcsbba", 2));
    }

    public static int longestSubstring1(String s, int k) {
        int n = s.length();
        if (n < k)
            return 0;
        char[] str = s.toCharArray();
        int[] cnt = new int[26];
        for (char c : str) {//统计字母出现的次数
            cnt[c - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            if (cnt[str[i] - 'a'] < k) {
                int l = longestSubstring1(s.substring(0, i), k);//左分治
                int r = longestSubstring1(s.substring(i + 1, n), k);//右分治
                return Math.max(l, r);
            }
        }
        return n;
    }
//好懂一些
    public int longestSubstring2(String s, int k) {
        if (s.length() < k) return 0;
        HashMap<Character, Integer> counter = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (char c : counter.keySet()) {
            if (counter.get(c) < k) {
                int res = 0;
                for (String t : s.split(String.valueOf(c))) {//split的妙用
                    res = Math.max(res, longestSubstring2(t, k));
                }
                return res;
            }
        }
        return s.length();
    }
}