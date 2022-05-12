package Tiger2022.March;
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
通过次数364,567提交次数711,851
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Z字形变换_6 {

    public String convert(String s, int numRows) {
        int l = s.length();
        if (l == 1 && l == numRows)
            return s;
        char[][] chars = new char[numRows][l];
        int a = 0, b = 0;
        for (int i = 0; i < l; ) {
            chars[a++][b] = s.charAt(i++);
            if (a == numRows) {
                --a;
                while (a > 0 && i < l) {
                    a--;
                    b++;
                    chars[a][b] = s.charAt(i++);
                }
                ++a;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (char[] aChar : chars) {
            for (char c : aChar) {
                if (Character.isLetter(c))
                    ans.append(c);
            }
        }
        return ans.toString();
    }

    @Test
    public void test() throws Exception {

        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        List<Integer> list = new ArrayList<>();
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 5));

    }

    public String convert1(String s, int numRows) {
        if (numRows < 2) return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows[i].append(c);
            if (i == 0 || i == numRows - 1) flag = -flag;
            i += flag;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) {
            ans.append(row);
        }
        return ans.toString();
    }
}
