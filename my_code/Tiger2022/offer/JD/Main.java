package Tiger2022.offer.JD;

import java.util.Arrays;
import java.util.Scanner;

/*
树上行走
时间限制： 3000MS
内存限制： 589824KB
题目描述：
A国是一个有个城市的满二叉树结构，其中这棵二叉树部分如下图



现在你在A国旅游，你目前在节点X，每一个小时你可以往上走或者往某一个儿子节点走。

具体地，你有以下三种操作：

- U：往上走一格；

- L：往左子树走一格；

- R：往右子树走一格

现在，N小时过去了，你想知道你目前所在的城市的编号。答案保证不超过1018且操作一定合法。



输入描述
第一行两个空格隔开的正整数N，X

接下来一行一个长度为N的只包含U,L,R三种字母的字符串。

对于90%的数据，1≤N≤100，1≤X＜210

对于100%的数据，1≤N≤5.105，1≤X＜230

输出描述
输出一行一个正整数，表示当前所在城市的编号。


样例输入
3 2
URL
样例输出
6

提示
样例中的路径为2 -> 1 -> 3 -> 6
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String x = sc.next();
        String s = sc.next();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'U') {
                x = devide(x);
            } else if (c == 'L') {
                x = multi(x, 0);
            } else {
                x = multi(x, 1);
            }
        }
        System.out.println(x);
    }

    private static String devide(String x) {
        char[] arr = x.toCharArray();
        int re = 0;
        for (int i = 0; i < x.length(); i++) {
            int cur = re * 10 + (arr[i] - '0');
            arr[i] = (char) ('0' + cur / 2);
            re = cur % 2;
        }
        int i = 0;
        while (i < arr.length && arr[i] == '0')
            ++i;
        StringBuilder sb = new StringBuilder();
        for (; i < x.length(); i++) {
            sb.append(arr[i]);
        }
        return sb.length() == 0 ? "1" : sb.toString();
    }

    private static String multi(String x, int re) {
        char[] arr = x.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = x.length() - 1; i >= 0; i--) {
            int num = arr[i] - '0';
            sb.append((num * 2 + re) % 10);
            re = (num * 2 + re) / 10;
        }
        return sb.reverse().toString();
    }
}

