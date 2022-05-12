package xsw.September;
/*
6. Z 字形变换
将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

P   A   H   N
A P L S I I G
Y   I   R
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);


示例 1：

输入：s = "PAYPALISHIRING", numRows = 3
输出："PAHNAPLSIIGYIR"
示例 2：
输入：s = "PAYPALISHIRING", numRows = 4
输出："PINALSIGYAHRPI"
解释：
P     I    N
A   L S  I G
Y A   H R
P     I
示例 3：

输入：s = "A", numRows = 1
输出："A"


提示：

1 <= s.length <= 1000
s 由英文字母（小写和大写）、',' 和 '.' 组成
1 <= numRows <= 1000
通过次数294,069提交次数580,620
 */

import java.util.ArrayList;
import java.util.*;

public class Z字形变换_6 {

    public static String convert(String s, int numRows) {
        int len = s.length();
        if (numRows == 1 || len == 1)
            return s;
        int c = numRows + (len - numRows) / 2;
        char[][] cc = new char[c][numRows];
        char[] chars = s.toCharArray();
        int index = 0;
        int i = 0, j = 0;
        while (index < len) {
            while (index < len && j < numRows)
                cc[i][j++] = chars[index++];
            --j;
            while (index < len && j > 0)
                cc[++i][--j] = chars[index++];
            ++j;
        }
        StringBuilder ans = new StringBuilder();
        for (int a = 0; a < numRows; a++) {
            for (int b = 0; b < c; b++) {
                if (cc[b][a] == ',' || Character.isLetter(cc[b][a]) || cc[b][a] == '.')
                    ans.append(cc[b][a]);
            }
        }
        //System.out.println(Arrays.deepToString(cc));
        return ans.toString();
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(convert("PAYPALISHIRING", 4));
    }

    // 真·棒
    /*
    设 numRows 行字符串分别为 s_1, s_2,..., s_n，
    则容易发现：按顺序遍历字符串 s 时，
    每个字符 c 在 Z 字形中对应的 行索引 先从 s_1 增大至 s_n，再从 s_n 减小至 s_1 …… 如此反复。
     */
    public String convert1(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}