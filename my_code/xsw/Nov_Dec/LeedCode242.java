package xsw.Nov_Dec;/*
242. 有效的字母异位词
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
*/

public class LeedCode242 {
    public static boolean isAnagram(String s, String t) {
        int[] letter = new int[26];
        for (char c : s.toCharArray()) {
            ++letter[c-'a'];
        }
        for (char ch : t.toCharArray()) {
            --letter[ch-'a'];
        }
        for (int i : letter) {
            if(i!=0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        boolean flag = isAnagram(s, t);
        System.out.println(flag);
    }

    //超时？
    public static boolean isAnagram2(String s, String t) {
        if(s.length()!=t.length())
            return false;
        int[] letter = new int[26];
        for (int i = 0; i < s.toCharArray().length; i++) {
            ++letter[s.charAt(i)-'a'];
            --letter[t.charAt(i)-'a'];
        }
        for (int i : letter) {
            if(i!=0)
                return false;
        }
        return true;
    }
}
