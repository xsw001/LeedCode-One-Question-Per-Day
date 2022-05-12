package xsw.Nov_Dec;/*
290. 单词规律
给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

示例1:

输入: pattern = "abba", str = "dog cat cat dog"
输出: true
示例 2:

输入:pattern = "abba", str = "dog cat cat fish"
输出: false
示例 3:

输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false
示例 4:

输入: pattern = "abba", str = "dog dog dog dog"
输出: false
说明:
你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LeedCode290 {
    public static void main(String[] args) {
        String pattern = "abc";
        String str = "dog cat dog";
        boolean flag = wordPattern1(pattern, str);
        System.out.println(flag);
    }

    public static boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> map = new HashMap<Character, String>();
        String[] strs = s.split(" ");

        if (strs.length != pattern.length())
            return false;
        for (int i = 0; i < pattern.length(); i++) {
            if (map.get(pattern.charAt(i)) != null) {
                if (!map.get(pattern.charAt(i)).equals(strs[i])) {
                    return false;
                }
            } else
                map.put(pattern.charAt(i), strs[i]);
        }
        HashSet<String> set = new HashSet<String>(map.values());
        return map.size() == set.size();
    }
    // 存在bug
/*    "abc"
            "dog cat dog"*/
    public static boolean wordPattern1(String pattern, String s) {
        int lenP = pattern.length();
        String[] str = s.split(" ");
        int lenS = str.length;
        if (lenS != lenP)
            return false;
        for (int i = 0; i < lenP; i++) {
            str[i] = pattern.charAt(i) + str[i];
        }
        Arrays.sort(str);
        for (int i = 0; i < str.length-1; i++) {
            if(str[i].charAt(0) == str[i+1].charAt(0) && !str[i].equals(str[i + 1]))
                return false;
            if(str[i].charAt(0) != str[i+1].charAt(0) && str[i].substring(1).equals(str[i + 1].substring(1)))
                return false;
        }
        return true;
    }
}
