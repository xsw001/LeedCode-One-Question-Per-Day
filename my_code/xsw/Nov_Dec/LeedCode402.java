package xsw.Nov_Dec;

import java.util.Deque;
import java.util.LinkedList;

/*
402. 移掉K位数字
给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。

注意:

num 的长度小于 10002 且 ≥ k。
num 不会包含任何前导零。
示例 1 :

输入: num = "432119", k = 3
输出: "119"
解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
示例 2 :

输入: num = "10200", k = 1
输出: "200"
解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
示例 3 :

输入: num = "10", k = 2
输出: "0"
解释: 从原数字移除所有的数字，剩余为空就是0。
*/
public class LeedCode402 {
    //思路错误
    public static String removeKdigits1(String num, int k) {
        if (k == num.length()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder(num);
        while (k != 0) {
            int temp = sb.charAt(0);
            int loc = 0;
            for (int i = 0; i < sb.length() - k; i++) {
                if (temp < sb.charAt(i)) {
                    temp = sb.charAt(i);
                    loc = i;
                }
            }
            sb.deleteCharAt(loc);
            --k;
        }
        while (sb.charAt(0) == '0' && sb.length() != 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String num = "4351191";
        int k = 3;
        String s = removeKdigits(num, k);
        System.out.println(s);
    }

    //自己的  时间复杂度高
    private static String removeKdigits(String num, int k) {
        StringBuilder sb = new StringBuilder(num);
        while (k != 0) {
            int flag = 0;
            for (int i = 0; i < sb.length()-1; i++) {
                if (sb.charAt(i) > sb.charAt(i+1)) {
                    sb.deleteCharAt(i);
                    flag = 1;
                    break;
                }
            }
            --k;
            //如果我们删除了 m 个数字且 m<k，这种情况下我们需要从序列尾部删除额外的 k-m 个数字。
            if (flag == 0)
                sb.deleteCharAt(sb.length() - 1);
            //System.out.println(sb);
        }
        //如果最终数字序列为空，我们应该返回 0。
        if (sb.length() == 0)
            return "0";
        //如果最终的数字序列存在前导零，我们要删去前导零。
        while (sb.charAt(0) == '0' && sb.length() > 1)
            sb.deleteCharAt(0);
/*        int zero = 0;
        while (sb.charAt(zero) == '0' && zero < sb.length() - 1)
            ++zero;
        if (zero > 0)
            sb.delete(0, zero);*/
        return sb.toString();
    }

    //考虑到栈的特点是后进先出，如果通过栈实现，则需要将栈内元素依次弹出然后进行翻转才能得到最小数
    //为了避免翻转操作，可以使用双端队列代替栈的实现。
    public static String removeKdigits2(String num, int k) {
        Deque<Character> deque = new LinkedList<Character>();
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            //对于每个数字，如果该数字小于栈顶元素，我们就不断地弹出栈顶元素，直到
            //栈为空
            //或者我们已经删除了 k 位数字
            //或者新的栈顶元素不大于当前数字
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            //栈中的元素代表截止到当前位置，删除不超过 kk 次个数字后，所能得到的最小整数。
            deque.offerLast(digit);
        }

        //如果我们删除了 m 个数字且 m<k，这种情况下我们需要从序列尾部删除额外的 k-m 个数字。
        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            //如果最终的数字序列存在前导零，我们要删去前导零。
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        //如果最终数字序列为空，我们应该返回 0。
        return ret.length() == 0 ? "0" : ret.toString();
    }

}
