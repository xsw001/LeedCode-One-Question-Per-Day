package xsw.August;
/*
345. 反转字符串中的元音字母
编写一个函数，以字符串作为输入，反转该字符串中的元音字母。



示例 1：

输入："hello"
输出："holle"
示例 2：

输入："leetcode"
输出："leotcede"


提示：

元音字母不包含字母 "y" 。
通过次数79,722提交次数153,165
请问您在哪类招聘中遇到此题？
 */

import java.util.ArrayList;

public class 反转字符串中的元音字母_345 {

    public static String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        int l = 0, r = n - 1;
        while (l < r) {
            while (l < n - 1 && s.charAt(l) != 'i' && s.charAt(l) != 'e' && s.charAt(l) != 'a' && s.charAt(l) != 'o' && s.charAt(l) != 'u' && s.charAt(l) != 'I' && s.charAt(l) != 'E' && s.charAt(l) != 'A' && s.charAt(l) != 'O' && s.charAt(l) != 'U')
                ++l;
            while (r > 0 && s.charAt(r) != 'i' && s.charAt(r) != 'e' && s.charAt(r) != 'a' && s.charAt(r) != 'o' && s.charAt(r) != 'u' && s.charAt(r) != 'I' && s.charAt(r) != 'E' && s.charAt(r) != 'A' && s.charAt(r) != 'O' && s.charAt(r) != 'U')
                --r;
            if (l < r) {
                char c = arr[l];
                arr[l] = arr[r];
                arr[r] = c;
                ++l;
                --r;
            }
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(reverseVowels("hello"));
    }

}