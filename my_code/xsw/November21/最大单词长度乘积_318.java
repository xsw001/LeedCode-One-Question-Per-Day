package xsw.November21;
/*
318. 最大单词长度乘积
给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。



示例 1:

输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
输出: 16
解释: 这两个单词为 "abcw", "xtfn"。
示例 2:

输入: ["a","ab","abc","d","cd","bcd","abcd"]
输出: 4
解释: 这两个单词为 "ab", "cd"。
示例 3:

输入: ["a","aa","aaa","aaaa"]
输出: 0
解释: 不存在这样的两个单词。


提示：

2 <= words.length <= 1000
1 <= words[i].length <= 1000
words[i] 仅包含小写字母
通过次数24,579提交次数34,895
 */

import org.junit.Test;

import java.util.*;

public class 最大单词长度乘积_318 {

    public int maxProduct(String[] words) {
        //Arrays.sort(words, ((o1, o2) -> o2.length() - o1.length()));
        int n = words.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!isSame(words[i], words[j]))
                    ans = Math.max(ans, words[i].length() * words[j].length());
            }
        }
        return ans;
    }

    private boolean isSame(String a, String b) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            set.add(a.charAt(i));
        }
        for (int i = 0; i < b.length(); i++) {
            if (set.contains(b.charAt(i)))
                return true;
        }
        return false;
    }

    @Test
    public void test() {
        String[] data = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(maxProduct(data));
        System.out.println(maxProduct1(data));
    }

    // 通过 位运算优化
    public int maxProduct1(String[] words) {
        int n = words.length;
        int[] bits = new int[n];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                bits[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((bits[i] & bits[j]) == 0)
                    ans = Math.max(ans, words[i].length() * words[j].length());
            }
        }
        return ans;
    }
}