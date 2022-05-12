package xsw.October;
/*
187. 重复的DNA序列
所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，
例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。

编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。



示例 1：

输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC","CCCCCAAAAA"]
示例 2：

输入：s = "AAAAAAAAAAAAA"
输出：["AAAAAAAAAA"]


提示：

0 <= s.length <= 105
s[i] 为 'A'、'C'、'G' 或 'T'
 */

import java.util.*;

public class 重复的DNA序列_187 {

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<String>();
        HashSet<String> set = new HashSet<>();
        int l = 0, r = 0;
        while (r <= s.length()) {
            if (r - l < 10)
                ++r;
            else {
                String sub = s.substring(l, r);
                if (!set.add(sub) && !ans.contains(sub))
                    ans.add(sub);
                ++l;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAAA"));
        System.out.println(Solution.findRepeatedDnaSequences("AAAAAAAAAAA"));

    }

    static class Solution {
        static final int L = 10;
        static Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }};

        public static List<String> findRepeatedDnaSequences(String s) {
            List<String> ans = new ArrayList<String>();
            int n = s.length();
            if (n <= L) {
                return ans;
            }
            int x = 0;
            for (int i = 0; i < L - 1; ++i) {
                x = (x << 2) | bin.get(s.charAt(i));
            }
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int i = 0; i <= n - L; ++i) {
                /*
滑动窗口向右移动一位：x = x << 2，由于每个字符用 2 个比特表示，所以要左移 2 位；
一个新的字符 ch 进入窗口：x = x | bin[ch]，这里 bin[ch] 为字符 ch 的对应二进制；
窗口最左边的字符离开窗口：x = x & ((1 << 20) - 1)，由于我们只考虑 x 的低 20 位比特，需要将其余位置零，即与上 (1 << 20) - 1。
                 */
                x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
                cnt.put(x, cnt.getOrDefault(x, 0) + 1);
                if (cnt.get(x) == 2) {
                    ans.add(s.substring(i, i + L));
                }
            }
            return ans;
        }
    }
//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/repeated-dna-sequences/solution/zhong-fu-de-dnaxu-lie-by-leetcode-soluti-z8zn/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}