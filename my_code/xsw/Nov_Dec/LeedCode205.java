package xsw.Nov_Dec;

/*
205. 同构字符串
给定两个字符串 s 和 t，判断它们是否是同构的。

如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

示例 1:

输入: s = "egg", t = "add"
输出: true
示例 2:

输入: s = "foo", t = "bar"
输出: false
示例 3:

输入: s = "paper", t = "title"
输出: true
说明:
你可以假设 s 和 t 具有相同的长度。
*/

import java.util.HashMap;

public class LeedCode205 {

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), t.charAt(i));
            } else {
                if (map.get(s.charAt(i)) != t.charAt(i))
                    return false;
            }
        }
        HashMap<Character, Character> map1 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map1.containsKey(t.charAt(i))) {
                map1.put(t.charAt(i), s.charAt(i));
            } else {
                if (map1.get(t.charAt(i)) != s.charAt(i))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "ad";
        String t = "aa";
        boolean isomorphic = isIsomorphic(s, t);
        System.out.println(isomorphic);
    }

    public static boolean isIsomorphic1(String s, String t) {
        if (s.length() != t.length())
            return false;

        for (int index = 0; index <= s.length() - 1; index++)
        {
            if (s.indexOf(s.charAt(index)) != t.indexOf(t.charAt(index)))
            {
                return false;
            }
        }
        return true;
    }
}
