package xsw.September;
/*
678. 有效的括号字符串
给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：

任何左括号 ( 必须有相应的右括号 )。
任何右括号 ) 必须有相应的左括号 ( 。
左括号 ( 必须在对应的右括号之前 )。
* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
一个空字符串也被视为有效字符串。
示例 1:

输入: "()"
输出: True
示例 2:

输入: "(*)"
输出: True
示例 3:

输入: "(*))"
输出: True
注意:

字符串大小将在 [1，100] 范围内。
通过次数20,672提交次数58,008
 */

import java.util.ArrayDeque;
import java.util.*;

public class 有效的括号字符串_678 {

    public static boolean checkValidString(String s) {
        int n = s.length();
        Deque<Integer> k = new ArrayDeque<>();
        Deque<Integer> x = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(')
                k.addFirst(i);
            else if (s.charAt(i) == ')') {
                if (k.isEmpty()) {
                    if (x.isEmpty())
                        return false;
                    else {
                        if (i > x.peekFirst())
                            x.pollFirst();
                        else return false;
                    }
                } else
                    k.pollFirst();
            } else
                x.addFirst(i);
        }
        while(!k.isEmpty()){
            if(x.isEmpty())
                return false;
            if(k.pollFirst() > x.pollFirst())
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(checkValidString("***((("));
    }

    /*
    方法三：贪心
使用贪心的思想，可以将空间复杂度降到 O(1)。

从左到右遍历字符串，遍历过程中，未匹配的左括号数量可能会出现如下变化：
    如果遇到左括号，则未匹配的左括号数量加 1；
    如果遇到右括号，则需要有一个左括号和右括号匹配，因此未匹配的左括号数量减 1；
    如果遇到星号，由于星号可以看成左括号、右括号或空字符串，因此未匹配的左括号数量可能加 1、减 1 或不变。

基于上述结论，可以在遍历过程中维护未匹配的左括号数量可能的最小值和最大值，根据遍历到的字符更新最小值和最大值：
    如果遇到左括号，则将最小值和最大值分别加 1；
    如果遇到右括号，则将最小值和最大值分别减 1；
    如果遇到星号，则将最小值减 1，将最大值加 1。

任何情况下，未匹配的左括号数量必须非负，因此当最大值变成负数时，说明没有左括号可以和右括号匹配，返回 false。

当最小值为 0 时，不应将最小值继续减少，以确保最小值非负。

遍历结束时，所有的左括号都应和右括号匹配，因此只有当最小值为 0 时，字符串 s 才是有效的括号字符串。
     */
    class Solution {
        public boolean checkValidString(String s) {
            int minCount = 0, maxCount = 0;
            int n = s.length();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    minCount++;
                    maxCount++;
                } else if (c == ')') {
                    minCount = Math.max(minCount - 1, 0);
                    maxCount--;
                    if (maxCount < 0) {
                        return false;
                    }
                } else {
                    minCount = Math.max(minCount - 1, 0);
                    maxCount++;
                }
            }
            return minCount == 0;
        }
    }

/*    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/valid-parenthesis-string/solution/you-xiao-de-gua-hao-zi-fu-chuan-by-leetc-osi3/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}