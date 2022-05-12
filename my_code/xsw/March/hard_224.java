package xsw.March;
/*
224. 基本计算器
实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。



示例 1：

输入：s = "1 + 1"
输出：2
示例 2：

输入：s = " 2-1 + 2 "
输出：3
示例 3：

输入：s = "(1+(4+5+2)-3)+(6+8)"
输出：23


提示：

1 <= s.length <= 3 * 105
s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
s 表示一个有效的表达式
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class hard_224 {

    //执行用时：    //1573 ms    //, 在所有 Java 提交中击败了    //5.02%    //的用户
    public static int calculate1(String s) {
        int res = 0;
        s = help(s);
        String str = s.replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "");
        if (str.charAt(0) != '-')
            str = "+" + str;
        while (str.length() > 1) {
            int i = 1;
            while (i < str.length() && str.charAt(i) != '+' && str.charAt(i) != '-') ++i;
            if (str.charAt(0) == '+') {
                res += Integer.parseInt(str.substring(1, i));
            } else {
                res -= Integer.parseInt(str.substring(1, i));
            }
            str = str.substring(i);
        }
        return res;
    }

    private static String help(String s) {
        String re = s.replaceAll(" ", "");
        for (int i = 0; i < re.length(); i++) {
            if (re.charAt(i) == '-' && re.charAt(i + 1) == '(') {
                re = change(i + 1, re);
            }
        }
        return re;
    }

    private static String change(int begin, String s) {
        StringBuilder builder = new StringBuilder(s);
        for (int i = begin + 1; i < builder.length(); i++) {
            if ((builder.charAt(i) == ')'))
                break;
            if (builder.charAt(i) == '+') {
                builder.replace(i, i + 1, "-");
            } else if (builder.charAt(i) == '-')
                builder.replace(i, i + 1, "+");
            else if (builder.charAt(i) == '(') {
                int num = 1;
                while (num > 0) {
                    ++i;
                    if (builder.charAt(i) == '(')
                        ++num;
                    if (builder.charAt(i) == ')')
                        --num;
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        //System.out.println(calculate("(1+( 4+5+2) -3)+(6+8)"));
        //System.out.println(calculate("(7)-(0)+(4)"));
        System.out.println(calculate("4 -(5-9)- 5"));
        //System.out.println(calculate("2-1+2"));
        //System.out.println(calculate("(1+( 4+5+2) -3)+(6+8)"));
        //System.out.println(calculate("(1+( 14 + 15+2) -3)+(6+8)"));
    }

    //沙拉吧唧的 气死了
    public static int calculate2(String s) {
        if (s.charAt(0) == '-') {
            s = "0" + s;
        }
        String str = mToA(s);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                int a = stack.pop();
                int b = stack.pop();
                if (str.charAt(i) == '+')
                    stack.push(a + b);
                else
                    stack.push(b - a);
            } else {
                stack.push(Integer.parseInt(String.valueOf(str.charAt(i))));
            }
        }
        return stack.peek();
    }

    private static String mToA(String s) {
        Stack<Character> stack = new Stack<Character>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                continue;
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                int n = 0;
                while (!stack.isEmpty()) {
                    if (stack.peek() == '(') {
                        stack.pop();
                        ++n;
                        if (n > 1)
                            break;
                    } else
                        res.append(stack.pop());
                }
            } else
                res.append(s.charAt(i));
        }
        while (!stack.isEmpty()) {
            Character c = stack.pop();
            if (c != '(')
                res.append(c);
        }
        return res.toString();
    }

    //我们考虑使用一个取值为 {−1,+1} 的整数 sign 代表「当前」的符号。根据括号表达式的性质，它的取值：
    /*：

与字符串中当前位置的运算符有关；
如果当前位置处于一系列括号之内，则也与这些括号前面的运算符有关：每当遇到一个以 -− 号开头的括号，则意味着此后的符号都要被「翻转」。
考虑到第二点，我们需要维护一个栈 \textit{ops}ops，其中栈顶元素记录了当前位置所处的每个括号所「共同形成」的符号。例如，对于字符串 \text{1+2+(3-(4+5))}1+2+(3-(4+5))：

扫描到 \text{1+2}1+2 时，由于当前位置没有被任何括号所包含，则栈顶元素为初始值 +1+1；
扫描到 \text{1+2+(3}1+2+(3 时，当前位置被一个括号所包含，该括号前面的符号为 ++ 号，因此栈顶元素依然 +1+1；
扫描到 \text{1+2+(3-(4}1+2+(3-(4 时，当前位置被两个括号所包含，分别对应着 ++ 号和 -− 号，由于 ++ 号和 -− 号合并的结果为 -− 号，因此栈顶元素变为 -1−1。
*/
    public static int calculate(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }
}