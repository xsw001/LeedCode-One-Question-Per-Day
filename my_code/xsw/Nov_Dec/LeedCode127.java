package xsw.Nov_Dec;/*
127. 单词接龙
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
示例 1:

输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出: 5

解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     返回它的长度 5。
*/

import java.util.ArrayList;
import java.util.List;

public class LeedCode127 {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int count = 0;
        int len = beginWord.length();
        for (int i = 0; i < len; i++) {
            if(beginWord.charAt(i) != endWord.charAt(i)){
                ++count;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord  = "cog";
        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        int i = ladderLength(beginWord, endWord, list);
        System.out.println(i);
    }
}
