package xsw.September;
/*
68. 文本左右对齐
给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。

你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。

要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。

文本的最后一行应为左对齐，且单词之间不插入额外的空格。

说明:

单词是指由非空格字符组成的字符序列。
每个单词的长度大于 0，小于等于 maxWidth。
输入单词数组 words 至少包含一个单词。
示例:

输入:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
输出:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
示例 2:

输入:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
输出:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     因为最后一行应为左对齐，而不是左右两端对齐。
     第二行同样为左对齐，这是因为这行只包含一个单词。
示例 3:

输入:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
通过次数21,328提交次数44,089
 */

import java.util.*;

public class 文本左右对齐_68 {

    static String[] w;

    public static List<String> fullJustify(String[] words, int maxWidth) {
        w = words;
        ArrayList<String> list = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < words.length; ) {
            int len = 0;
            int num = 0;
            char c = 0;
            while (i < words.length && len + words[i].length() <= maxWidth) {
                len += words[i++].length();
                c = '+';
                if (len < maxWidth) {
                    ++len;
                    c = ' ';
                }
                ++num;
            }
            if (i == words.length) { //当前行是最后一行: 单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
                StringBuilder builder = new StringBuilder();
                int j = start;
                while (j < w.length && j < start + num) { // 单词左对齐，且单词之间应只有一个空格
                    builder.append(w[j++]);
                    if (builder.length() < maxWidth)
                        builder.append(" ");
                }
                builder.append(" ".repeat(Math.max(0, maxWidth - len))); // 在行末填充剩余空格
                list.add(builder.toString());
                break;
            }
            if (num > 1) { //当前行不是最后一行，且不只一个单词
                String s = average(len, start, num - 1, maxWidth, c);
                list.add(s);
                start += num;
            } else { // 当前行不是最后一行，且只有一个单词：该单词左对齐，在行末填充剩余空格
                if (w[start].length() == maxWidth)
                    list.add(w[start]);
                else
                    list.add(w[start] + " ".repeat(Math.max(0, maxWidth - len + 1)));
                ++start;
            }
        }
        return list;
    }

    private static String average(int len, int start, int num, int maxWidth, char c) {
        int total = num + (c == ' ' ? 1 + maxWidth - len : 0);
        int avg = total / num;
        StringBuilder blank = new StringBuilder();
        blank.append(" ".repeat(Math.max(0, avg)));
        int remainder = total % num;
        StringBuilder builder = new StringBuilder();
        int i = start;
        while (i < w.length && i < start + num) {
            builder.append(w[i++]).append(blank);
            if (remainder > 0) {
                builder.append(" ");
                --remainder;
            }
        }
        builder.append(w[start + num]);
        return builder.toString();
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        String[] arr = {"Listen", "to", "many,", "speak", "to", "a", "few."};
        System.out.println(fullJustify(arr, 6));
    }

}