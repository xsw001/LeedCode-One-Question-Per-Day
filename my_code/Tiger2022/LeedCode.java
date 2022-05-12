package Tiger2022;
/*
题目：给你一个只包含 '(' 和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。

示例 1：

输入：s = "(()"，

输出：2，

解释：最长有效括号子串是 "()"

示例 2：

输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"

 */

import org.junit.Test;

import java.util.*;

public class LeedCode {

    @Test
    public void test() throws Exception {
        int[] a = {1,2,5,6,8,9,12};
        System.out.println(findLargestElement(a));
    }

    public int findLargestElement(int[] array) {
        int max = -1;
        for (int num : array) {
            if (num > max)
                max = num;
        }
        return max;
    }

}
