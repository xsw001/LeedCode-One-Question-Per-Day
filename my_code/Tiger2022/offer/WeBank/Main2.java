package Tiger2022.offer.WeBank;

/*
数字串
时间限制： 3000MS
内存限制： 589824KB
题目描述：
      小明有一个长度为n的仅由0到9组成的字符串。小美想知道这个串里有多少个子串代表的十进制数能被k整除。

      字符串a的子串即是那些可被描述为“由a中第i至第j个字符组成的字符串”的串。如字符串‘121’有‘1’，‘2’，‘1’，‘12’，‘21’，‘121’六个子串。



输入描述
第一行有两个整数n(1<=n<=1000),k(1<=k<=1000)，代表这个数字串的长度和询问的数。

第二行有一个长度为n的数字串。

输出描述
输出一个整数，代表给出的数字串中有多少个子串满足其对应的十进制数能被k整除。

如样例中的‘1101’，‘012’，‘12’，‘0’都能被3整除。


样例输入
5 3
11012
样例输出
4
 */

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String k = sc.next();
        String s = sc.next();
        BigInteger bk = new BigInteger(k);
        BigInteger zero = new BigInteger("0");
        HashSet<String> set = new HashSet<>(500500);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = s.substring(i, j);
                if (set.contains(sub)) {
                    ++ans;
                    continue;
                }
                if (sub.length() < 19) {
                    long l = Long.parseLong(sub);
                    if (l % Long.parseLong(k) == 0) {
                        ++ans;
                        set.add(sub);
                    }
                } else {
                    BigInteger bi = new BigInteger(sub);
                    BigInteger[] temp = bi.divideAndRemainder(bk);
                    if (temp[1].compareTo(zero) == 0) {
                        ++ans;
                        set.add(sub);
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
/*
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String k = sc.next();
        String s = sc.next();
        BigInteger bk = new BigInteger(k);
        BigInteger zero = new BigInteger("0");
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = s.substring(i, j);
                BigInteger bi = new BigInteger(sub);
                BigInteger[] temp = bi.divideAndRemainder(bk);
                if (temp[1].compareTo(zero) == 0)
                    ++ans;

            }
        }
        System.out.println(ans);
 */

/*
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String k = sc.next();
        String s = sc.next();
        BigInteger bk = new BigInteger(k);
        BigInteger zero = new BigInteger("0");
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = s.substring(i, j);
                if (sub.length() < 19) {
                    long l = Long.parseLong(sub);
                    if (l % Long.parseLong(k) == 0)
                        ++ans;
                } else {
                    BigInteger bi = new BigInteger(sub);
                    BigInteger[] temp = bi.divideAndRemainder(bk);
                    if (temp[1].compareTo(zero) == 0)
                        ++ans;
                }
            }
        }
        System.out.println(ans);
 */