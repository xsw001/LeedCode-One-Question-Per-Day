package xsw.March.美团面试;
/*
小美得到了一款游戏——斗龙。小美拥有两个技能，每个技能都能秒杀掉一条龙
但是要付出相应的MP值，第一个技能需要c1点MP值，第二个技能需要c2点MP值。只要MP足够，小美可以使用无限次技能。

小美即将遇到 n 条龙，如果不使用技能，她和第 i 条龙的战斗结果是T或者F
而如果使用任何一个技能战斗结果都是T。T表示小美成功打败龙，而F表示小美被龙打败
如果小美被龙连续打败三次，那小美就会输掉游戏。请你帮忙计算小美最少需要多少点 MP才能通关。



输入描述
第一行三个数 n, c1, c2。(1 ≤ n ≤ 100000，1 ≤ c1, c2 ≤ 1000000000)。

第二行 n 个字符，第 i 个字符 si 代表小美与第 i 场战斗的结果。si 是 T 代表小美打败龙，si 是 F 代表小美被龙打败。

输出描述
输出一个数，代表小美最少需要的MP值。


样例输入
10 7 3
FTFFFTFFFF
样例输出
6

提示
小美可以在第3场战斗、第8场战斗中使用第二个技能，需要耗费3×2=6点MP。
*/

import java.util.Scanner;

public class 小美斗龙 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int c1 = cin.nextInt();
        int c2 = cin.nextInt();
        int c = Math.min(c1, c2);
        String str = cin.next();

        int res = 0,freq = 0;
        for (int i = 0; i < n; i++) {
            if(str.charAt(i)=='F'){
                ++freq;
                if(freq == 3){
                    res += c;
                    freq = 0;
                }
            }else
                freq = 0;
        }
        System.out.println(res);
    }
}
/*
10 7 3
FFFFFTFFFF
*/
