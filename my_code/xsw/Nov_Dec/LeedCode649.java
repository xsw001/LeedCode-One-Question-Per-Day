package xsw.Nov_Dec;/*
649. Dota2 参议院
Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)

Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。
在每一轮中，每一位参议员都可以行使两项权利中的一项：

禁止一名参议员的权利：
          参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。

宣布胜利：
          如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。

给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。

以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。

假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。



示例 1：

输入："RD"
输出："Radiant"
解释：第一个参议员来自 Radiant 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。
然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人
示例 2：

输入："RDD"
输出："Dire"
解释：
第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利
第二个来自 Dire 阵营的参议员会被跳过因为他的权利被禁止
第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利
因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利


提示：

给定字符串的长度在 [1, 10000] 之间.
*/

import java.util.LinkedList;
import java.util.Queue;

public class LeedCode649 {

    //两个问题：
    //1. 只能进行一轮
    //2. 被投出去的人还能投
    public static String predictPartyVictory1(String senate) {
        int r = 0, d = 0;
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R')
                ++r;
            else
                ++d;
        }
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                --d;
                if (d == 0)
                    return "Radiant";
            } else {
                --r;
                if (r == 0)
                    return "Dire";
            }
        }
        return r == 0 ? "Radiant" : "Dire";
    }

    //执行结果：通过
    //执行用时： 109 ms , 在所有 Java 提交中击败了 15.50% 的用户
    //内存消耗： 38.7 MB , 在所有 Java 提交中击败了 74.61% 的用户
    public static String predictPartyVictory(String senate) {
        int r = 0, d = 0;
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R')
                ++r;
            else
                ++d;
        }
        if (r == 0 || d == 0)
            return d == 0 ? "Radiant" : "Dire";
        StringBuilder sb = new StringBuilder(senate);
        int len = sb.length();
        int i = 0;
        while (true) {
            if (sb.charAt(i) == 'R') {
                --d;
                if (d == 0)
                    return "Radiant";
                int j = i + 1;
                while (j < sb.length()) {
                    if (sb.charAt(j) == 'D')
                        break;
                    ++j;
                }
                if (j == sb.length())
                    j = sb.indexOf("D");
                sb.deleteCharAt(j);
                --len;
            } else {
                --r;
                if (r == 0)
                    return "Dire";
                int j = i + 1;
                while (j < sb.length()) {
                    if (sb.charAt(j) == 'R')
                        break;
                    ++j;
                }
                if (j == sb.length())
                    j = sb.indexOf("R");
                sb.deleteCharAt(j);
                --len;
            }
            i++;
            if (i >= len)
                i = 0;
        }
    }

    public static void main(String[] args) {
        String senate = "RRDDD";
        String victory = predictPartyVictory(senate);
        System.out.println(victory);
    }

    //思路一样样，实现比我厉害多了
    public static String predictPartyVictory2(String senate) {
        Queue<Integer> radiantQueue = new LinkedList<>();
        Queue<Integer> direQueue = new LinkedList<>();
        char[] senateChar = senate.toCharArray();
        int length = senate.length();
        for (int i = 0; i < length; i++) {
            //分别把R和D的索引放到两个队列中
            if (senateChar[i] == 'R')
                radiantQueue.add(i);
            else
                direQueue.add(i);
        }
        while (!radiantQueue.isEmpty() && !direQueue.isEmpty()) {
            //两个队列同时出队，这里出队的是R和D在字符串S中的索引
            int indexRadiant = radiantQueue.poll();
            int indexDire = direQueue.poll();
            //然后比较索引值，小的可以消掉大的，也就是前面的可以消掉后面的。
            //因为每一轮只能消掉一个，把另一个消掉之后还要把当前字符的索引重新加
            //入到队列中，为了防止这一轮中他再次消掉其他字符，要把他的索引加上length，
            if (indexRadiant < indexDire)
                radiantQueue.add(indexRadiant + length);
            else
                direQueue.add(indexDire + length);
        }
        //然后判断不为空的获胜
        return direQueue.isEmpty() ? "Radiant" : "Dire";
    }
}
