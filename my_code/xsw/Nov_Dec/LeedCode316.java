package xsw.Nov_Dec;/*
316. 去除重复字母
给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同

示例 1：

输入：s = "bcabc"
输出："abc"
示例 2：

输入：s = "cbacdcbc"
输出："acdb"
提示：

1 <= s.length <= 104
s 由小写英文字母组成
*/

import java.util.LinkedList;

public class LeedCode316 {
    public static String removeDuplicateLetters1(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ++count[s.charAt(i)-'a'];
        }
        LinkedList<Character> list = new LinkedList<Character>();
        list.addLast(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if(list.contains(s.charAt(i))) {
                --count[s.charAt(i) - 'a'];
                continue;
            }
            if(s.charAt(i) > list.peekLast()) {
                list.addLast(s.charAt(i));
            }else{
                while(!list.isEmpty()){
                    if(s.charAt(i) < list.peekLast()) {
                        if (count[list.peekLast() - 'a'] > 1) {
                            --count[list.peekLast() - 'a'];
                            list.pollLast();
                        } else
                            break;
                    }else
                        break;
                }
                list.addLast(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "bddbccd";
        String letters = removeDuplicateLetters(s);
        System.out.println(letters);
    }

    public static String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        int len = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            lastIndex[chars[i]-'a'] = i;
        }
        boolean[] visited = new boolean[26];
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if(visited[chars[i]-'a'])
                continue;

            while(!list.isEmpty() && list.peekLast()>chars[i] && lastIndex[list.peekLast()-'a']>i) {
                Character last = list.pollLast();
                visited[last-'a'] = false;
            }

            list.addLast(chars[i]);
            visited[chars[i]-'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            sb.append(c);
        }
        return sb.toString();
    }
}
