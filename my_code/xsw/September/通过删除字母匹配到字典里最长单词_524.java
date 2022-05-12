package xsw.September;
/*
524. 通过删除字母匹配到字典里最长单词
给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。

如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。



示例 1：

输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
输出："apple"
示例 2：

输入：s = "abpcplea", dictionary = ["a","b","c"]
输出："a"


提示：

1 <= s.length <= 1000
1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 1000
s 和 dictionary[i] 仅由小写英文字母组成
通过次数43,483提交次数90,888
 */

import java.util.*;

public class 通过删除字母匹配到字典里最长单词_524 {

    public static String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((o1, o2) -> {
            if (o1.length() == o2.length()) {
                for (int i = 0; i < o1.length(); i++) {
                    if(o1.charAt(i)== o2.charAt(i))
                        continue;
                    return o1.charAt(i) - o2.charAt(i);
                }
            }
            return o2.length() - o1.length();
        });
        for (String sub : dictionary) {
            if (isSubStr(s, sub))
                return sub;
        }
        return "";
    }

    private static boolean isSubStr(String s, String sub) {
        int a = 0, b = 0;
        while (a < s.length() && b < sub.length()) {
            if (s.charAt(a) == sub.charAt(b))
                ++b;
            ++a;
        }
        return b == sub.length();
    }

    public static void main(String[] args) {
        String[] data = {"word","good","best","good"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(data));
        System.out.println(findLongestWord("wordgoodgoodgoodbestword", list));
        System.out.println(list);
    }

}