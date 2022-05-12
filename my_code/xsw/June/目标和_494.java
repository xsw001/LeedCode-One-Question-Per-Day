package xsw.June;
/*
494. 目标和
给你一个整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。



示例 1：

输入：nums = [1,1,1,1,1], target = 3
输出：5
解释：一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
示例 2：

输入：nums = [1], target = 1
输出：1


提示：

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 100
通过次数90,936提交次数195,266
 */

//又是背包问题？那直接贴出力扣上的背包问题吧：
//
//01背包：416. 分割等和子集 474. 一和零 494. 目标和
//
//完全背包：1449. 数位成本和为目标值的最大数字 322. 零钱兑换 518. 零钱兑换 II 279. 完全平方数

public class 目标和_494 {

    static class Node {
        int val;
        int sum;
        Node left;
        Node right;

        public Node() {
            val = 0;
            sum = 0;
            left = right = null;
        }

        public Node(int val, int sum) {
            this.val = val;
            this.sum = sum;
        }

        public Node(int val, int sum, Node left, Node right) {
            this.val = val;
            this.sum = sum;
            this.left = left;
            this.right = right;
        }
    }

    static int ans;
    static int in = 0;

    // 超时了
    public static int findTargetSumWays(int[] nums, int target) {
        ans = 0;
        Node node = new Node(nums[0], nums[0]);
        creat(nums, node, 1, target);
        return ans;
    }

    private static void creat(int[] nums, Node node, int i, int target) {
        if (i == nums.length) {
            if (Math.abs(node.sum) == Math.abs(target)) {
                ++ans;
                if (target == 0)
                    ++ans;
            }
            return;
        }
        int l = nums[i];
        int r = -nums[i];
        int sum = node.sum;
        System.out.println(sum + " " + in++);
        Node lnode = new Node(l, sum + l);
        Node rnode = new Node(r, sum + r);
        node.left = lnode;
        node.right = rnode;
        creat(nums, node.left, i + 1, target);
        creat(nums, node.right, i + 1, target);
    }


    public static void main(String[] args) {
        int[] data = {1, 1, 1, 1};
        System.out.println(findTargetSumWays(data, 0));

    }

    // 写了个开头就没写了的回溯
    public int findTargetSumWays1(int[] nums, int target) {
        ans = 0;
        back(nums, target, 0, 0);
        return  ans;
    }

    private void back(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (target == sum)
                ++ans;
        } else {
            back(nums, target, index + 1, sum + nums[index]);
            back(nums, target, index + 1, sum - nums[index]);
        }
    }

    // 动规
    // 定义二维数组 dp，其中 dp[i][j] 表示在数组 nums 的前 i 个数中选取元素
    // 使得这些元素之和等于 j 的方案数。假设数组 nums 的长度为 n，则最终答案为 dp[n][neg]。

    public int findTargetSumWays2(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int diff = sum - target;    // 根据推导，diff必须是2的倍数，且 大于0
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }

        int neg = diff / 2; // 根据推导，要想数组“计算和”为target，数组中相应的负数和
        int length = nums.length;
        int[][] dp = new int[length + 1][neg + 1];  // dp[i][j]表示：前i个数字中，组成和为j的可能性，有 dp[i][j]种
        dp[0][0] = 1;   // 初始化有一种结果
        for (int i = 1; i <= length; i++) {
            int curNum = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                /*
                    若 j < curNum，则 dp[i][j] 的个数为 dp[i - 1][j]的个数(不用当前数字)
                    若 j >= curNum，则 dp[i][j] 的个数为 dp[i - 1][j]的个数(不用当前数字) 和 dp[i - 1][j - num]的个数(用当前数字) 的和
                */
                dp[i][j] = dp[i - 1][j];
                if (j >= curNum) {
                    dp[i][j] += dp[i - 1][j - curNum];
                }
            }
        }

        return dp[length][neg];
    }
}