package xsw.November21;
/*
423. 从英文中重建数字
给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。



示例 1：

输入：s = "owoztneoer"
输出："012"
示例 2：

输入：s = "fviefuro"
输出："45"


提示：

1 <= s.length <= 105
s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
s 保证是一个符合题目要求的字符串
通过次数12,469提交次数21,051

温馨提示：
0的英文是zero，1的英文是one，2的英文是two，3的英文是three，4的英文是four，5的英文是five，
6的英文是six，7的英文是seven，8的英文是eight，9的英文是nine。
 */

import org.junit.Test;

import java.util.*;
import java.util.PriorityQueue;

public class 从英文中重建数字_423 {

    public String originalDigits(String s) {
        int[] words = new int[26];
        int[] ans = new int[10];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'z') {
                ans[0]++;
                for (int j = 0; j < 4; j++) {
                    words["zero".charAt(j) - 'a']--;
                }
            } else if (c == 'w') {
                ans[2]++;
                for (int j = 0; j < 3; j++) {
                    words["two".charAt(j) - 'a']--;
                }
            } else if (c == 'u') {
                ans[4]++;
                for (int j = 0; j < 4; j++) {
                    words["four".charAt(j) - 'a']--;
                }
            } else if (c == 'x') {
                ans[6]++;
                for (int j = 0; j < 3; j++) {
                    words["six".charAt(j) - 'a']--;
                }
            } else if (c == 'g') {
                ans[8]++;
                for (int j = 0; j < 5; j++) {
                    words["eight".charAt(j) - 'a']--;
                }
            }
            words[c - 'a']++;
        }
        ans[1] += words['o' - 'a'];
        ans[3] += words['t' - 'a'];
        ans[5] += words['f' - 'a'];
        words['i' - 'a'] -= words['f' - 'a'];
        ans[7] += words['s' - 'a'];
        ans[9] += words['i' - 'a'];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (ans[i] > 0) {
                sb.append(("" + i).repeat(ans[i]));
            }
        }
        return sb.toString();
    }


    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(originalDigits("owoztneoefviefuror"));
    }

    class Solution {
        public String originalDigits(String s) {
            Map<Character, Integer> c = new HashMap<Character, Integer>();
            for (int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                c.put(ch, c.getOrDefault(ch, 0) + 1);
            }

            int[] cnt = new int[10];
            cnt[0] = c.getOrDefault('z', 0);
            cnt[2] = c.getOrDefault('w', 0);
            cnt[4] = c.getOrDefault('u', 0);
            cnt[6] = c.getOrDefault('x', 0);
            cnt[8] = c.getOrDefault('g', 0);

            cnt[3] = c.getOrDefault('h', 0) - cnt[8];
            cnt[5] = c.getOrDefault('f', 0) - cnt[4];
            cnt[7] = c.getOrDefault('s', 0) - cnt[6];

            cnt[1] = c.getOrDefault('o', 0) - cnt[0] - cnt[2] - cnt[4];

            cnt[9] = c.getOrDefault('i', 0) - cnt[5] - cnt[6] - cnt[8];

            StringBuffer ans = new StringBuffer();
            for (int i = 0; i < 10; ++i) {
                for (int j = 0; j < cnt[i]; ++j) {
                    ans.append((char) (i + '0'));
                }
            }
            return ans.toString();
        }
    }

}