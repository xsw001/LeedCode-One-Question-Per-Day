package xsw.Nov_Dec;/*

514. 自由之路
视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。

给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。

最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。

旋转 ring 拼出 key 字符 key[i] 的阶段中：

您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
示例：
    输入: ring = "godding", key = "gd"
    输出: 4
    解释:
     对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
     对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
     当然, 我们还需要1步进行拼写。
     因此最终的输出是 4。
提示：
    ring 和 key 的字符串长度取值范围均为 1 至 100；
    两个字符串中都只有小写字符，并且均可能存在重复字符；
    字符串 key 一定可以由字符串 ring 旋转拼出
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeedCode514晕乎乎的 {

    //自己的代码,只考虑到了当前最优，没有考虑到当前的选择对以后的影响
    public static int findRotateSteps(String ring, String key) {
        int steps = 0;
        StringBuilder sb = new StringBuilder(ring);
        for (int i = 0; i < key.length(); i++) {
            char k = key.charAt(i);
            //System.out.println(sb + " " + k);
            int[] step = findEachStep(sb, k);
            //System.out.println(step[0]);
            steps += step[0] + 1;

            if (step[1] == 0) {
                String substring = sb.substring(0, step[0]);
                sb.delete(0, step[0]);
                sb.append(substring);
            } else {
                String substring = sb.substring(0, sb.length() - step[0]);
                sb.delete(0, sb.length() - step[0]);
                sb.append(substring);
            }
            System.out.print(steps + "                                      ");
        }
        return steps;
    }

    public static int[] findEachStep(StringBuilder ring, char k) {
        int[] result = new int[2];
        int l = 0, r = 0, flag = 0;
        int len = ring.length();
        for (int i = 0; i < len; i++) {
            if (ring.charAt(i) == k) {
                r = i;
                break;
            }
        }
        //System.out.println("r  " + r);
        char c = ring.charAt(0);
        ring.deleteCharAt(0).reverse().insert(0, c);
        //System.out.println(ring);
        for (int i = 0; i < len; i++) {
            if (ring.charAt(i) == k) {
                l = i;
                break;
            }
        }
        //System.out.println("l  " + l);
        char c1 = ring.charAt(0);
        ring.deleteCharAt(0).reverse().insert(0, c1);
        result[0] = Math.min(r, l);
        if (r > l)
            flag = 1;
        result[1] = flag;
        //System.out.println("step： " + flag);
        return result;
    }

    public static void main(String[] args) {
        String ring = "iotfo", key = "fioot";
        int i = findRotateSteps1(ring, key);
        System.out.println(i);
        System.out.println(findRotateSteps(ring, key));
    }

    //官方答案
    public static int findRotateSteps1(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26];//表示字符 i 在 ring 中出现的位置集合
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        //System.out.println(Arrays.toString(pos));
        //[[], [], [], [], [], [], [3], [], [], [], [], [4], [], [0, 2]
        // [], [], [], [], [], [], [], [], [], [], [1], []]

        //定义 dp[i][j]表示从前往后拼写出key的第 i 个字符，ring 的第j个字符与12:00方向对齐的最少步数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }
        //System.out.println(Arrays.deepToString(dp));
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, n - i) + 1;
        }
        //System.out.println(Arrays.deepToString(dp));
        for (int i = 1; i < m; ++i) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    //Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1
                    //表示在当前第 k 个字符与 12:00 方向对齐时第 j 个字符旋转到 12:00 方向并按下拼写的最少步数。
                    int a = dp[i][j];
                    int b = Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1;
                    int c = dp[i - 1][k] + b;//累加的过程，截止到上一步的步数dp[i - 1][k] 加上 当前的步数 b
                    dp[i][j] = Math.min(a, c);
                }
            }
        }
//        for (int i = 1; i < m; ++i) {
//            for (int j : pos[key.charAt(i) - 'a']) {
//                    dp[i][j] = Math.min(j, n - j) + 1;
//                }
//        }
        System.out.println(Arrays.deepToString(dp));
        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }

    //时间最短的
    public static int findRotateSteps2(String ring, String key) {
        final int n = ring.length();
        final int[] memo = new int[n];
        final int[] count = new int[26];
        final char[] cs = ring.toCharArray();
        for (char c : cs) {
            count[c - 'a']++;
        }
        final int[][] positions = new int[26][];
        for (int i = 0; i < 26; i++) {
            positions[i] = new int[count[i]];
        }
        Arrays.fill(count, 0);
        for (int i = 0; i < n; i++) {
            positions[cs[i] - 'a'][count[cs[i] - 'a']++] = i;
        }
        for (int idx : positions[key.charAt(0) - 'a']) {
            memo[idx] = 1 + distance(0, idx, n);
        }
        for (int idx = 1; idx != key.length(); idx++) {
            int il = key.charAt(idx - 1) - 'a', ic = key.charAt(idx) - 'a';
            int[] cur = positions[ic];
            if (il == ic) {
                for (int i : cur) {
                    memo[i]++;
                }
                continue; // special ops. should be right
            }
            int[] last = positions[il];
            for (int i : cur) {
                int midDistance = Integer.MAX_VALUE; // note. cannot n
                for (int j : last) {
                    midDistance = Math.min(midDistance, memo[j] + distance(i, j, n));
                }
                memo[i] = midDistance + 1;
            }
        }
        int res = Integer.MAX_VALUE; // note cannot n
        for (int idx : positions[key.charAt(key.length() - 1) - 'a']) {
            res = Math.min(res, memo[idx]);
        }
        return res;
    }

    private static int distance(int src, int dest, final int n) {
        if (src < dest) {
            return Math.min(dest - src, n - dest + src);
        } else {
            return Math.min(src - dest, n - src + dest);
        }
    }
}
