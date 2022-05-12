package Tiger2022.offer.Java360;

import java.util.HashMap;
import java.util.Scanner;

/*
寻找子串
时间限制： 3000MS
内存限制： 589824KB
题目描述：
英语老师看你老是在英语课上和周围同学交头接耳，所以给你布置了一份额外的家庭作业来惩罚惩罚你：你有一个字符串s，请你在s的所有子串中，找到出现次数最多的子串，告诉老师它的出现次数。



输入描述
共一行，一个字符串s，仅由英文小写字母组成，1≤|s|≤10000。

输出描述
一个正整数，表示最大出现次数。


样例输入
aba
样例输出
2

提示
aba的所有子串为a、b、a、ab、ba、aba，其中a的出现次数最多，出现了2次。
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int ans = 0;
        int[] arr = new int[26];
        HashMap<String, Integer> map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i)-'a']++;
            ans = Math.max(ans, arr[s.charAt(i)-'a']);
        }
        System.out.println(ans);
    }
}
