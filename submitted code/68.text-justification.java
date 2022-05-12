//
// @lc app=leetcode.cn id=68 lang=java
//
// [68] text-justification
//
class Solution {
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
            if (i == words.length) {
                StringBuilder builder = new StringBuilder();
                int j = start;
                while (j < w.length && j < start + num) {
                    builder.append(w[j++]);
                    if (builder.length() < maxWidth)
                        builder.append(" ");
                }
                builder.append(" ".repeat(Math.max(0, maxWidth - len)));
                list.add(builder.toString());
                break;
            }
            if (num > 1) {
                String s = average(len, start, num - 1, maxWidth,c);
                list.add(s);
                start += num;
            } else {
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
}
// @lc code=end