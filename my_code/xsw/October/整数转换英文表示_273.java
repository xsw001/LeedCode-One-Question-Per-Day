package xsw.October;
/*
将非负整数 num 转换为其对应的英文表示。

 

示例 1：

输入：num = 123
输出："One Hundred Twenty Three"
示例 2：

输入：num = 12345
输出："Twelve Thousand Three Hundred Forty Five"
示例 3：

输入：num = 1234567
输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
示例 4：

输入：num = 1234567891
输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 

提示：

0 <= num <= 231 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/integer-to-english-words
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;

public class 整数转换英文表示_273 {

    static String[] dan = {"Hundred", "Thousand", "Million", "Billion"};
    static String[] less20 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static String[] multiple10 = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        ArrayList<Integer> list = new ArrayList<>();
        int t = 1000;
        while (num > 0) {
            list.add(num % t);
            num /= t;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            Integer number = list.get(i);
            String str = transform(number);
            if (str.equals(""))
                continue;
            sb.append(str).append(" ");
            if (i > 0)
                sb.append(dan[i]).append(" ");
        }
        return sb.toString().trim();
    }

    private static String transform(int num) {
        StringBuilder sb = new StringBuilder();
        int l = num / 100;
        num %= 100;
        if (l != 0)
            sb.append(less20[l]).append(" ").append(dan[0]).append(" ");
        if (num == 0)
            return sb.toString().trim();
        if (num < 20)
            sb.append(less20[num]);
        else if (num % 10 == 0)
            sb.append(multiple10[num / 10 - 2]);
        else
            sb.append(multiple10[num / 10 - 2]).append(" ").append(less20[num % 10]);
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(numberToWords(0));
    }

}