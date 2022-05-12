package xsw.October;
/*
282. 给表达式添加运算符
给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。



示例 1:

输入: num = "123", target = 6
输出: ["1+2+3", "1*2*3"]
示例 2:

输入: num = "232", target = 8
输出: ["2*3+2", "2+3*2"]
示例 3:

输入: num = "105", target = 5
输出: ["1*0+5","10-5"]
示例 4:

输入: num = "00", target = 0
输出: ["0+0", "0-0", "0*0"]
示例 5:

输入: num = "3456237490", target = 9191
输出: []


提示：

1 <= num.length <= 10
num 仅含数字
-231 <= target <= 231 - 1
 */

import java.util.*;

public class 给表达式添加运算符_282 {
    /*
    好嘛，错误理解题意
        "123854652"
         4895
         ["1*238+5+4652"]
     */
    static ArrayList<String> ans;
    static ArrayList<String> symbol;
    static String[] s = {"+", "-", "*"};

    public static List<String> addOperators(String num, int target) {
        ans = new ArrayList<>();
        symbol = new ArrayList<>();
        symbol.addAll(Arrays.asList(s));
        for (int i = 1; i < num.length() - 1; i++) {
            ArrayList<String> list = new ArrayList<>();
            for (String o : symbol) {
                list.add(o + "+");
                list.add(o + "-");
                list.add(o + "*");
            }
            symbol = list;
        }
        for (String str : symbol) {
            multi(num, str, target);
        }
        return ans;
    }

    private static void multi(String num, String str, int target) {
        int l = num.length();
        StringBuilder sb = new StringBuilder().append(num.charAt(0));
        for (int i = 1; i < l; i++) {
            sb.append(str.charAt(i - 1)).append(num.charAt(i));
        }
        Deque<Integer> stackInt = new ArrayDeque<Integer>();
        Deque<Character> stackSys = new ArrayDeque<>();
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (Character.isDigit(c))
                stackInt.addFirst(c - '0');
            else if (c == '*')
                stackSys.addFirst(c);
            else {
                if (!stackSys.isEmpty() && stackSys.peekFirst() == '*') {
                    stackInt.addFirst(stackInt.pollFirst() * stackInt.pollFirst());
                }
                stackSys.addFirst(c);

            }
        }
        while (stackInt.size() > 1) {
            int a = stackInt.pollFirst();
            int b = stackInt.pollFirst();
            char d = stackSys.pollFirst();
            if (d == '+')
                stackInt.addFirst(a + b);
            else if (d == '*') {
                stackInt.addFirst(a * b);
            } else
                stackInt.addFirst(b - a);
        }
        if (stackInt.peek() == target)
            ans.add(sb.toString());
    }

    private static void mul(String num, String str, int target) {
        int res = num.charAt(0) - '0';
        StringBuilder sb = new StringBuilder();
        sb.append(res);
        for (int i = 1; i < num.length(); i++) {
            char c = str.charAt(i - 1);
            if (c == '+') {
                res += (num.charAt(i) - '0');
            } else if (c == '-')
                res -= (num.charAt(i) - '0');
            else
                res *= (num.charAt(i) - '0');
            sb.append(c).append(num.charAt(i));
        }
        if (res == target)
            ans.add(sb.toString());
    }


    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        String num = "123854652";
        int target = 4895;
        System.out.println(addOperators(num, target));
    }
/*
第一版
    static ArrayList<String> ans;

    public static List<String> addOperators(String num, int target) {
        ans = new ArrayList<>();
        back(num, 1, num.charAt(0) + "", num.charAt(0) - '0', target);
        return ans;
    }

    private static void back(String num, int start, String s, int total, int target) {
        if (total == target) {
            ans.add(s);
            return;
        }
        for (int i = start; i < num.length(); i++) {
            back(num, start + 1, s + "+" + num.charAt(i), total + (num.charAt(i) - '0'), target);
            back(num, start + 1, s + "-" + num.charAt(i), total - (num.charAt(i) - '0'), target);
            back(num, start + 1, s + "*" + num.charAt(i), total * (num.charAt(i) - '0'), target);
        }
    }
* */

    class Solution {
        /*
        除了记录当前决策到原串 num 的哪一位 u，以及当前的运算结果 cur 以外
        还需要额外记录最后一次的计算结果 prev，然后在决策表达式中的第 k 个部分时，对本次添加的运算符合做分情况讨论
         */
        List<String> ans = new ArrayList<>();
        String s;
        int n, t;

        public List<String> addOperators(String num, int target) {
            s = num;
            n = s.length();
            t = target;
            dfs(0, 0, 0, "");
            return ans;
        }

        void dfs(int u, long prev, long cur, String ss) {
            if (u == n) {
                if (cur == t) ans.add(ss);
                return;
            }
            for (int i = u; i < n; i++) {
                if (i != u && s.charAt(u) == '0') break; //0 单独作为一位是被允许的，但是多位且首部为 0 是不允许的
                long next = Long.parseLong(s.substring(u, i + 1));// 这就是我理解错题意的地方。题中是可以要求把连续几位看成一个数的
                if (u == 0) {
                    dfs(i + 1, next, next, "" + next); // 这也是细节
                } else {
                    dfs(i + 1, next, cur + next, ss + "+" + next);
                    dfs(i + 1, -next, cur - next, ss + "-" + next);
                    long x = prev * next;
                    dfs(i + 1, x, cur - prev + x, ss + "*" + next);
                }
            }
        }
    }

/*    作者：AC_OIer
    链接：https://leetcode-cn.com/problems/expression-add-operators/solution/gong-shui-san-xie-hui-su-suan-fa-yun-yon-nl9z/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

}