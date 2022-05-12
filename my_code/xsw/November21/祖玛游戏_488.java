package xsw.November21;
/*
488. 祖玛游戏
你正在参与祖玛游戏的一个变种。

在这个祖玛游戏变体中，桌面上有 一排 彩球，每个球的颜色可能是：红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W' 。你的手中也有一些彩球。

你的目标是 清空 桌面上所有的球。每一回合：

从你手上的彩球中选出 任意一颗 ，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。
接着，如果有出现 三个或者三个以上 且 颜色相同 的球相连的话，就把它们移除掉。
如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则可以继续移除这些球，直到不再满足移除条件。
如果桌面上所有球都被移除，则认为你赢得本场游戏。
重复这个过程，直到你赢了游戏或者手中没有更多的球。
给你一个字符串 board ，表示桌面上最开始的那排球。另给你一个字符串 hand ，表示手里的彩球。请你按上述操作步骤移除掉桌上所有球，计算并返回所需的 最少 球数。如果不能移除桌上所有的球，返回 -1 。



示例 1：

输入：board = "WRRBBW", hand = "RB"
输出：-1
解释：无法移除桌面上的所有球。可以得到的最好局面是：
- 插入一个 'R' ，使桌面变为 WRRRBBW 。WRRRBBW -> WBBW
- 插入一个 'B' ，使桌面变为 WBBBW 。WBBBW -> WW
桌面上还剩着球，没有其他球可以插入。
示例 2：

输入：board = "WWRRBBWW", hand = "WRBRW"
输出：2
解释：要想清空桌面上的球，可以按下述步骤：
- 插入一个 'R' ，使桌面变为 WWRRRBBWW 。WWRRRBBWW -> WWBBWW
- 插入一个 'B' ，使桌面变为 WWBBBWW 。WWBBBWW -> WWWW -> empty
只需从手中出 2 个球就可以清空桌面。
示例 3：

输入：board = "G", hand = "GGGGG"
输出：2
解释：要想清空桌面上的球，可以按下述步骤：
- 插入一个 'G' ，使桌面变为 GG 。
- 插入一个 'G' ，使桌面变为 GGG 。GGG -> empty
只需从手中出 2 个球就可以清空桌面。
示例 4：

输入：board = "RBYYBBRRB", hand = "YRBGB"
输出：3
解释：要想清空桌面上的球，可以按下述步骤：
- 插入一个 'Y' ，使桌面变为 RBYYYBBRRB 。RBYYYBBRRB -> RBBBRRB -> RRRB -> B
- 插入一个 'B' ，使桌面变为 BB 。
- 插入一个 'B' ，使桌面变为 BBB 。BBB -> empty
只需从手中出 3 个球就可以清空桌面。


提示：

1 <= board.length <= 16
1 <= hand.length <= 5
board 和 hand 由字符 'R'、'Y'、'B'、'G' 和 'W' 组成
桌面上一开始的球中，不会有三个及三个以上颜色相同且连着的球
通过次数4,910提交次数11,458
 */

import java.util.*;

public class 祖玛游戏_488 {

    class Solution {
        public HashMap<String, Integer> cache;

        public int findMinStep(String board, String hand) {
            int[] hands = new int[5];
            cache = new HashMap<>();
            for (int i = 0; i < hand.length(); i++) hands[getId(hand.charAt(i))]++;
            return solve(board, hands);
        }

        public int solve(String board, int[] hands) {
            //消消乐
            board = xiaoxiaole(board);
            if (board.length() == 0) return 0;
            //用string方式固定状态放入缓存中
            String node = board + Arrays.toString(hands);
            if (cache.containsKey(node)) return cache.get(node);
            int res = -1;//默认找不到
            for (int i = 0; i < hands.length; i++) {
                //挑选hand，进行回溯
                if (hands[i] == 0) continue;
                hands[i]--;
                for (int j = 0; j < board.length(); j++) {
                    //枚举当前球应该放的位置
                    int ans = solve(board.substring(0, j) + getChar(i) + board.substring(j), hands);
                    if (ans != -1) res = res == -1 ? ans + 1 : Math.min(res, ans + 1);
                }
                hands[i]++;  //进行回溯
            }
            cache.put(node, res);
            return res;
        }

        private String xiaoxiaole(String board) {
            int s = 0;
            int length = board.length();
            for (int i = 1; i <= board.length(); i++) {
                if (i == board.length() || board.charAt(i) != board.charAt(i - 1)) {
                    //只有字符不连续才会进入if 当前s下标是连续字符串的第一个，i下标是不连续字符串的第一个
                    if (i - s >= 3) return xiaoxiaole(board.substring(0, s) + board.substring(i));
                    else s = i;   //更新s下标
                }
            }
            return board;
        }

        //解析那种颜色球的下标
        private int getId(char c) {
            if (c == 'W') return 0;
            else if (c == 'B') return 1;
            else if (c == 'Y') return 2;
            else if (c == 'G') return 3;
            else return 4;
        }

        //获取球的颜色
        private char getChar(int id) {
            if (id == 0) return 'W';
            else if (id == 1) return 'B';
            else if (id == 2) return 'Y';
            else if (id == 3) return 'G';
            else return 'R';
        }
    }
/*
    作者：yu2000324
    链接：https://leetcode-cn.com/problems/zuma-game/solution/bao-li-dfstong-guo-hui-su-jie-jue-can-ka-25yn/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}