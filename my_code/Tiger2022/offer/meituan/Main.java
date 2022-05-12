package Tiger2022.offer.meituan;

import java.util.HashMap;
import java.util.Scanner;

/*
字符串重排
时间限制： 3000MS
内存限制： 589824KB
题目描述：
给你一个只包含小写字符的字符串s，你可以按任意顺序重排这个字符串中的字符，请问重排过后的字符串中，最多能有多少个’acbcca’子串？

例如，字符串’dacbccab’中含1个’acbcca’子串，按其他方式重排后最多也只能包含1个’acbcca’子串；
字符串’acbccaacccb’中含1个’acbcca’子串，但重排成’acbccacbcca’ 后包含了2个’acbcca’子串。



输入描述
一行，一个只包含小写字符的字符串s。

数据保证 ：1<=|s|<=10000

输出描述
输出一个整数，表示将输入的字符串s重排后，最多能包含多少个’acbcca’子串。


样例输入
dacbccab
样例输出
1

提示
输入样例2

acbccaacccb



输出样例2

2
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        System.out.println(Math.min(Math.min(map.get('a')-1, map.get('b')), map.get('c')/3));
    }
}
