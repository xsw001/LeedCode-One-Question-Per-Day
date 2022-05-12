package Tiger2022.offer.meituan;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
中位数
时间限制： 3000MS
内存限制： 589824KB
题目描述：
小团很喜欢中位数。现在给定一个序列，若其长度为奇数，那么其中位数是将序列中的数从小到大排序后位于正中间位置的数；
若其长度为偶数，那么其中位数是将序列中的数从小到大排序后位于最中间的两个数的平均值。

现在给你一个长度为n的序列，小团想知道所有长度为奇数的区间的中位数之和为多少。



输入描述
第一行一个正整数n，表示序列中有n个数。

接下来一行n个空格隔开的正整数a1,a2,…an表示序列中n个数的值。

1<=n<=2000, 1<=ai<=100000，保证ai互不相同。

输出描述
一行一个正整数，表示给定序列中所有长度为奇数的区间的中位数之和。


样例输入
4
2 3 1 4
样例输出
15

提示
样例解释

长度为奇数的区间有[2], [3], [1], [4], [2 3 1], [3 1 4]

答案为2+3+1+4+2+3=15
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            ans += arr[i];
        }
        PriorityQueue<Integer> a = new PriorityQueue<>(); // 小, cun da
        PriorityQueue<Integer> b = new PriorityQueue<>((o1, o2) -> o2 - o1);// 大 cun xiao
        int l = 0;
        while (l < n) {
            a.add(arr[l]);
            int r = l + 1;
            while (r < n) {
                int i = 0;
                for (; r < n && i < 2; i++) {
                    if (a.size() != b.size()) {
                        a.add(arr[r]);
                        b.add(a.poll());
                    } else {
                        b.add(arr[r]);
                        a.add(b.poll());
                    }
                    ++r;
                }
                if (i == 2)
                    ans += a.peek();
            }
            l++;
            a.clear();
            b.clear();
        }
        System.out.println(ans);
    }
}
