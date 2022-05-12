package xsw.November21;
/*
859. 亲密字符串
给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。

交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。

例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。


示例 1：

输入：s = "ab", goal = "ba"
输出：true
解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
示例 2：

输入：s = "ab", goal = "ab"
输出：false
解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
示例 3：

输入：s = "aa", goal = "aa"
输出：true
解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
示例 4：

输入：s = "aaaaaaabc", goal = "aaaaaaacb"
输出：true


提示：

1 <= s.length, goal.length <= 2 * 104
s 和 goal 由小写英文字母组成
通过次数34,393提交次数108,644
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class 亲密字符串_859 {

    public boolean buddyStrings(String s, String g) {
        if (s.length() != g.length())
            return false;
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        String s1 = "", s2 = "";
        int n = s.length();
        boolean f = false;
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            char b = g.charAt(i);
            map1.put(a, map1.getOrDefault(a, 0) + 1);
            map2.put(b, map2.getOrDefault(b, 0) + 1);
            if (map1.get(a) > 1)
                f = true;
            if (a != b) {
                if (s1.equals(""))
                    s1 += "" + a + b;
                else if (s2.equals(""))
                    s2 += "" + a + b;
                else
                    return false;
            }
        }
        if (s1.equals("")) {
            return f;
        }
        return s1.charAt(0) == s2.charAt(1) && s1.charAt(1) == s2.charAt(0);
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        String a = "ab";
        String b = "ba";
        System.out.println(buddyStrings(a, b));
    }

    class Solution {
        public boolean buddyStrings(String s, String goal) {
            int n = s.length(), m = goal.length();
            if (n != m) return false;
            int[] cnt1 = new int[26], cnt2 = new int[26];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int a = s.charAt(i) - 'a', b = goal.charAt(i) - 'a';
                cnt1[a]++; cnt2[b]++;
                if (a != b) sum++;
            }
            boolean ok = false;
            for (int i = 0; i < 26; i++) {
                if (cnt1[i] != cnt2[i]) return false;
                if (cnt1[i] > 1) ok = true;
            }
            return sum == 2 || (sum == 0 && ok);
        }
    }

}