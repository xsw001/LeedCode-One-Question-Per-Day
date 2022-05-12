package Tiger2022.March;
/*
2049. 统计最高分的节点数目
给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。
同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。

一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。
求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。

请你返回有 最高得分 节点的 数目 。



示例 1:

example-1

输入：parents = [-1,2,0,2,0]
输出：3
解释：
- 节点 0 的分数为：3 * 1 = 3
- 节点 1 的分数为：4 = 4
- 节点 2 的分数为：1 * 1 * 2 = 2
- 节点 3 的分数为：4 = 4
- 节点 4 的分数为：4 = 4
最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
示例 2：

example-2

输入：parents = [-1,2,0]
输出：2
解释：
- 节点 0 的分数为：2 = 2
- 节点 1 的分数为：2 = 2
- 节点 2 的分数为：1 * 1 = 1
最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。


提示：

n == parents.length
2 <= n <= 105
parents[0] == -1
对于 i != 0 ，有 0 <= parents[i] <= n - 1
parents 表示一棵二叉树。
通过次数5,293提交次数11,296
 */

import org.junit.Test;

import java.util.*;

public class 统计最高分的节点数目_2049 {

/*    @Test
    public void test() throws Exception {

        int[] data = {-1, 0, 5, 8, 0, 1, 4, 4, 1, 6, 7};
        System.out.println(countHighestScoreNodes(data));
    }*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        int n = split.length;
        int[] arr = new int[n];
        for (int i = 1; i < n - 1; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        arr[0] = Integer.parseInt(split[0].substring(1));
        arr[n - 1] = Integer.parseInt(split[n - 1].substring(0, split[n - 1].length() - 1));
        //System.out.println(Arrays.toString(arr));


//        int[] data = {-1, 0, 5, 8, 0, 1, 4, 4, 1, 6, 7};
//        System.out.println(new Solution().countHighestScoreNodes(data));
        System.out.println(countHighestScoreNodes(arr));
        Solution sss = new Solution();
        System.out.println(sss.countHighestScoreNodes(arr));
    }

    // 超时
    static int[][] children;

    public static int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        children = new int[n][2];
        for (int[] child : children) {
            Arrays.fill(child, -1);
        }
        HashSet<Integer> nonLeaf = new HashSet<>();
        for (int i = 1; i < parents.length; i++) {
            if (children[parents[i]][0] == -1) {
                children[parents[i]][0] = i;
            } else
                children[parents[i]][1] = i;
            nonLeaf.add(parents[i]);
        }
        int ans = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            long temp;
            if (!nonLeaf.contains(i)) {
                temp = n - 1;
            } else {
                int left = count(children[i][0]);
                int right = count(children[i][1]);
                int father = n - right - left - 1;
                temp = help(left) * help(right) * help(father);
            }
            if (temp > max) {
                ans = 1;
                max = temp;
            } else if (temp == max) {
                ++ans;

            }
        }
        System.out.println(max);
        return ans;
    }

    private static long help(int count) {
        return count == 0 ? 1 : count;
    }

    private static int count(int cur) {
        if (cur == -1)
            return 0;
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(cur);
        int num = 0;
        while (!list.isEmpty()) {
            Integer i = list.pollLast();
            ++num;
            if (children[i][0] != -1)
                list.addFirst(children[i][0]);
            if (children[i][1] != -1)
                list.addFirst(children[i][1]);
        }
        return num;
    }

    static class Solution {
        long maxScore = 0;
        int ans = 0;
        int n;

        public int countHighestScoreNodes(int[] parents) {
            // 删除一个节点最多把整颗树分割成三部分：左子树、右子树、父节点及父节点的另一半子树
            // 所以，我们可以遍历每个节点的左右子树的数目，父节点及父节点的另一半子树的数量就等于 总节点数 减去 左右子树的数目 再减 一
            // 三者相乘就是分数，没有的部分用 1 代替
            // 而我们需要先构造出来这颗树才能通过DFS遍历
            this.n = parents.length;

            int[] lefts = new int[n];
            int[] rights = new int[n];
            Arrays.fill(lefts, -1);
            Arrays.fill(rights, -1);

            for (int i = 1; i < n; i++) {
                int p = parents[i];
                if (lefts[p] == -1) {
                    lefts[p] = i;
                } else {
                    rights[p] = i;
                }
            }

            dfs(0, lefts, rights);

            return ans;
        }

        private int dfs(int node, int[] lefts, int[] rights) {
            if (node == -1) {
                return 0;
            }

            int leftCount = dfs(lefts[node], lefts, rights);
            int rightCount = dfs(rights[node], lefts, rights);
            int remain = n - leftCount - rightCount - 1;

            long score = help(leftCount) * help(rightCount) * help(remain);
            if (score == maxScore) {
                ans++;
            } else if (score > maxScore) {
                maxScore = score;
                ans = 1;
            }

            return leftCount + rightCount + 1;
        }

        private long help(int count) {
            return count == 0 ? 1 : count;
        }
    }
}
