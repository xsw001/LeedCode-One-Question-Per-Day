package Tiger2022.offer.meituan;

import java.util.Scanner;

/*
会合
时间限制： 3000MS
内存限制： 589824KB
题目描述：
数轴上有n个点，从左到右编号分别为1,2,…,n。

小美在1号点，小团在n号点，现在要选择一个点作为他们会合的地点，他们期望选择的点能让小美和小团到达会合点的距离差值尽量小。

你的任务是输出最小的距离差。



输入描述
第1行是一个正整数n，表示数轴上有n个点。

第2行是n个空格隔开的正整数a1,a2,…,an，第 i 个数表示第 i 个点的坐标。

2<=n<=100000，1<=ai<=200，保证a1<=a2<=…<=an

输出描述
输出一个整数，表示最小距离差值。


样例输入
2
5 7
样例输出
2

提示
输入样例2

3

5 8 9



输出样例2

2
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int a = arr[0], b = arr[n - 1];
        int ans = b-a;
        for (int i = 1; i < n - 1; i++) {
            ans = Math.min(ans, Math.abs((arr[i] - a) - (b - arr[i])));
        }
        System.out.println(ans);
    }
}
