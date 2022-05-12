package Tiger2022.February;
/*
1405. 最长快乐字符串
如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。

给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：

s 是一个尽可能长的快乐字符串。
s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
s 中只含有 'a'、'b' 、'c' 三种字母。
如果不存在这样的字符串 s ，请返回一个空字符串 ""。



示例 1：

输入：a = 1, b = 1, c = 7
输出："ccaccbcc"
解释："ccbccacc" 也是一种正确答案。
示例 2：

输入：a = 2, b = 2, c = 1
输出："aabbc"
示例 3：

输入：a = 7, b = 1, c = 0
输出："aabaa"
解释：这是该测试用例的唯一正确答案。


提示：

0 <= a, b, c <= 100
a + b + c > 0
通过次数13,788提交次数23,069
 */

import org.junit.Test;

import java.util.*;

public class 最长快乐字符串_1405 {

    /*
        执行用时：13 ms, 在所有 Java 提交中击败了6.73%的用户
        内存消耗：41.1 MB, 在所有 Java 提交中击败了5.29%的用户
     */
    public String longestDiverseString1(int a, int b, int c) {
        StringBuilder ans = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        if (a > 0)
            list.add(a + "a");
        if (b > 0)
            list.add(b + "b");
        if (c > 0)
            list.add(c + "c");
        while (list.size() > 0) {
            list.sort((o2, o1) -> Integer.parseInt(o1.substring(0, o1.length() - 1)) - Integer.parseInt(o2.substring(0, o2.length() - 1)));
            String s = list.remove(0);
            int num = Integer.parseInt(s.substring(0, s.length() - 1));
            char ch = s.charAt(s.length() - 1);
            if (ans.length() < 2 || ch != ans.charAt(ans.length() - 1) || ch != ans.charAt(ans.length() - 2)) {
                ans.append(ch);
                if (num > 1)
                    list.add(num - 1 + "" + ch);
            } else {
                if (list.size() == 0)
                    break;
                list.add(s);
                s = list.remove(0);
                num = Integer.parseInt(s.substring(0, s.length() - 1));
                ch = s.charAt(s.length() - 1);
                ans.append(ch);
                if (num > 1)
                    list.add(num - 1 + "" + ch);
            }
        }
        return ans.toString();
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(longestDiverseString1(1, 1, 7));
    }

    class Solution {
        public String longestDiverseString(int a, int b, int c) {
            StringBuilder res = new StringBuilder();
            Pair[] arr = {new Pair(a, 'a'), new Pair(b, 'b'), new Pair(c, 'c')};

            while (true) {
                Arrays.sort(arr, (p1, p2) -> p2.freq - p1.freq);
                boolean hasNext = false;
                for (Pair pair : arr) {// 用循环加continue 和 break 控制第几个，高高高！
                    if (pair.freq <= 0) {
                        break;
                    }
                    int m = res.length();
                    if (m >= 2 && res.charAt(m - 2) == pair.ch && res.charAt(m - 1) == pair.ch) {
                        continue; // 秀
                    }
                    hasNext = true;
                    res.append(pair.ch);
                    pair.freq--;
                    break;// 秀
                }
                if (!hasNext) {
                    break;
                }
            }

            return res.toString();
        }

        class Pair {
            int freq;
            char ch;

            public Pair(int freq, char ch) {
                this.freq = freq;
                this.ch = ch;
            }
        }
    }

}