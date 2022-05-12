package xsw.January;
/*
567. 字符串的排列
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

示例1:

输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").


示例2:

输入: s1= "ab" s2 = "eidboaoo"
输出: False


注意：

输入的字符串只包含小写字母
两个字符串的长度都在 [1, 10,000] 之间
 */

import java.util.Arrays;

public class LeedCode567 {

    public static boolean checkInclusion(String s1, String s2) {
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i)-'a']++;
        }
        int left = 0, right = 0;
        while (right < s2.length()){
            if(freq[s2.charAt(right)-'a'] != 0){
                freq[s2.charAt(right)-'a']--;
                ++right;
            }else{
                freq[s2.charAt(left)-'a']++;
                ++left;
            }
            if(right-left == s1.length())
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "bd";
        String s2 = "eidboaoo";
        System.out.println(checkInclusion(s1, s2));
    }
}