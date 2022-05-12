package Tiger2022.offer.meituan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
/*
异步操作
时间限制： 3000MS
内存限制： 589824KB
题目描述：
小美因为各种原因无法按时返校。为了完成学业，小美只能在家里上网课。网课由n个课程视频组成，编号为1到n。
每个视频都需要从头看到尾才算完成该部分的学习，且某些视频只能在看完指定的一些视频之后才能开始播放。
因为小美的学习能力和她用来上网课的电脑性能都很强，所以小美可以同时观看任意多个课程视频。
现在小美想知道，若她不眠不休地学习，每个课程视频最早能在她开始学习多长时间之后才能结束。



输入描述
第一行有一个正整数n(1<=n<=500) ，代表课程视频的数量。

接下来n行中的第i行开头有两个正整数L,C(1<=L<=100,0<=C<n)。L代表编号为 i 的视频的时长为L分钟。C代表该观看该视频之前需要看完指定的C个视频。若C为零，则该视频可以立即观看。否则这一行接下来有C个用空格分开的正整数，代表编号为i的视频所依赖的那些课程视频的编号。

数字间两两有空格隔开

输出描述
在一行内输出n个用空格分开的整数，第 i 个代表编号为 i 的课程视频最早在开始后多少分钟之后才能看完。


样例输入
3
4 0
2 2 1 3
3 0
样例输出
4 6 3

提示
如样例1所描述：共有3个视频，第一个时长为4分钟且可以立即观看。第二个时长为2分钟且只能在看完编号为1和3的视频后才能开始播放。第三个时长为3分钟且可以立即观看。因此小美可以同时播放1和3两个视频，并在1号视频播放完毕后开始播放2号视频。
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, Integer> zero = new HashMap<>();
        HashMap<Integer, Integer> nozero = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int time = sc.nextInt();
            int c = sc.nextInt();
            if (c == 0) {
                zero.put(i, time);
            } else {
                nozero.put(i, time);
                for (int j = 0; j < c; j++) {
                    int t = sc.nextInt();
                    ArrayList<Integer> arrayList = map.getOrDefault(i, new ArrayList<>());
                    arrayList.add(t);
                    map.put(i, arrayList);
                }
            }
        }
        while (!nozero.isEmpty()) {
            for (Integer k : map.keySet()) {
                if (!nozero.containsKey(k))
                    continue;
                ArrayList<Integer> list = map.get(k);
                int max = 0;
                boolean f = false;
                for (Integer t : list) {
                    if (!zero.containsKey(t)) {
                        f = true;// 有任务没处理完
                        break;
                    }
                    max = Math.max(max, zero.get(t));
                }
                if (f)
                    continue;
                zero.put(k, max + nozero.remove(k));
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(zero.get(i));
            if(i != n)
                System.out.print(" ");
        }

    }
}
