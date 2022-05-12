package xsw.March;
/*
227. 基本计算器 II
给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

整数除法仅保留整数部分。



示例 1：

输入：s = "3+2*2"
输出：7
示例 2：

输入：s = " 3/2 "
输出：1
示例 3：

输入：s = " 3+5 / 2 "
输出：5


提示：

1 <= s.length <= 3 * 105
s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
s 表示一个 有效表达式
表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
题目数据保证答案是一个 32-bit 整数
 */

import java.util.Stack;

public class medium_227 {

    //执行用时：    //15 ms    //, 在所有 Java 提交中击败了    //51.16%    //的用户
    public static int calculate(String s) {
        Stack<Character> opt = new Stack<>();
        Stack<Integer> num = new Stack<>();

        if (s.charAt(0) == '-')
            s = "0" + s;
        int l = s.length();
        for (int i = 0; i < l;) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                while (!opt.isEmpty() &&  priority(s.charAt(i)) <= priority(opt.peek())) {
                    help(opt, num);
                }
                opt.push(s.charAt(i++));
            } else {
                int number = 0;
                while (i < l && Character.isDigit(s.charAt(i))) {
                    number = number * 10 + s.charAt(i) - '0';
                    i++;
                }
                num.push(number);
            }
        }
        while(!opt.isEmpty()){
            help(opt, num);
        }
        return num.peek();
    }

    private static void help(Stack<Character> opt, Stack<Integer> num) {
        char c = opt.pop();
        int a = num.pop();
        int b = num.pop();
        int temp = cal(a, b, c);
        num.push(temp);
    }

    private static int cal(int a, int b, char c) {
        if (c == '-')
            return b - a;
        else if (c == '+')
            return b + a;
        else if (c == '*')
            return b * a;
        else
            return b / a;
    }

    private static int priority(char c) {
        if (c == '-' || c == '+')
            return 0;
        else
            return 1;
    }


    public static void main(String[] args) {
        String s = " 13+520 / 20 +12*11";
        System.out.println(calculate(s));
    }

}