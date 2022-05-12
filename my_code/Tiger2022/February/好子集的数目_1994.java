package Tiger2022.February;
/*
1994. 好子集的数目
给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。

比方说，如果 nums = [1, 2, 3, 4] ：
[2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
[1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
请你返回 nums 中不同的 好 子集的数目对 109 + 7 取余 的结果。

nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。



示例 1：

输入：nums = [1,2,3,4]
输出：6
解释：好子集为：
- [1,2]：乘积为 2 ，可以表示为质数 2 的乘积。
- [1,2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
- [1,3]：乘积为 3 ，可以表示为质数 3 的乘积。
- [2]：乘积为 2 ，可以表示为质数 2 的乘积。
- [2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
- [3]：乘积为 3 ，可以表示为质数 3 的乘积。
示例 2：

输入：nums = [4,2,3,15]
输出：5
解释：好子集为：
- [2]：乘积为 2 ，可以表示为质数 2 的乘积。
- [2,3]：乘积为 6 ，可以表示为互不相同质数 2 和 3 的乘积。
- [2,15]：乘积为 30 ，可以表示为互不相同质数 2，3 和 5 的乘积。
- [3]：乘积为 3 ，可以表示为质数 3 的乘积。
- [15]：乘积为 15 ，可以表示为互不相同质数 3 和 5 的乘积。


提示：

1 <= nums.length <= 105
1 <= nums[i] <= 30
通过次数3,460提交次数6,590
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class 好子集的数目_1994 {

    final int MOD = 1000000007;

    // 无法解决含有 可以分解含有相同的质数 的情况 eg：[6,10,14]、[11,6,10,14]
    public int numberOfGoodSubsets1(int[] nums) {
        int[] count = new int[31];
        for (int num : nums) {
            count[num]++;
        }
        int prime = 0;
        HashSet<Integer> primeSet = new HashSet<>();
        Collections.addAll(primeSet, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29); //4, 8, 9, 12, 16, 18, 20, 24, 25, 27, 28, 30
        for (int i = 2; i < count.length; i++) {
            if (count[i] > 0 && primeSet.contains(i))
                ++prime;
        }
        int ans = 0;
        for (int i = 0; i < prime; i++) {
            ans = ans * 2 + 1;
        }
        ans = (ans + count[6] * (prime - count[2] - count[3] + 2)) % MOD;
        ans = (ans + count[10] * (prime - count[2] - count[5] + 2)) % MOD;
        ans = (ans + count[14] * (prime - count[2] - count[7] + 2)) % MOD;
        ans = (ans + count[15] * (prime - count[5] - count[3] + 2)) % MOD;
        ans = (ans + count[21] * (prime - count[7] - count[3] + 2)) % MOD;
        ans = (ans + count[22] * (prime - count[2] - count[11] + 2)) % MOD;
        ans = (ans + count[26] * (prime - count[2] - count[13] + 2)) % MOD;
        while (count[1] > 0) {
            ans = (ans * 2) % MOD;
            --count[1];
        }
        for (Integer i : primeSet) {
            while (count[i] > 1) {
                ans = (ans * 2) % MOD;
                --count[i];
            }
        }
        return ans;
    }

    @Test
    public void test() throws Exception {

        int[] data = {6, 10, 14};

        List<Integer> list = new ArrayList<>();
        System.out.println(numberOfGoodSubsets(data));
    }

    int[] p = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    int[] cnts = new int[35];

    public int numberOfGoodSubsets(int[] nums) {
        int n = nums.length;
        for (int i : nums) cnts[i]++;
        int mask = 1 << 10;
        // 定义 f[state] 为当前子集乘积拆解结果的用到的质数为 state 时的方案数
        // state 为一个长度 10 的二进制数，若 state 中的第 k 位二进制表示为 1，代表数值 p[k] 在拆解结果中出现过
        long[] f = new long[mask];
        f[0] = 1;
        for (int i = 2; i <= 30; i++) {
            if (cnts[i] == 0)
                continue;
            // 对 i 进行试除
            int cur = 0, x = i;
            boolean flag = true;
            for (int j = 0; j < 10; j++) {
                int t = p[j], c = 0;
                while (x % t == 0) {
                    cur |= (1 << j);
                    x /= t;
                    ++c;
                }
                // 如果 i 能够被同一质数试除多次，说明 i 不能加到子集，跳过
                if (c > 1) {
                    flag = false;
                    break;
                }
            }
            if (!flag)
                continue;
            // 枚举前一状态 prev
            //（确保考虑一个新数值 i 时，依赖的子集 prev 存储的为尚未考虑 i 的方案数）
            for (int pre = mask - 1; pre >= 0; --pre) {
                // 只有当前选择数与前一状态不冲突，则能够进行转移，将方案数进行累加
                if ((pre & cur) != 0) continue;
                f[pre | cur] = (f[pre | cur] + f[pre] * cnts[i]) % MOD;
            }
        }
        long ans = 0;
        // 统计所有非空集的方案数
        for (int i = 1; i < mask; i++) {
            ans = (ans + f[i]) % MOD;
        }
        // 在此基础上，考虑每个 1 选择与否对答案的影响
        for (int i = 0; i < cnts[1]; i++)
            ans = ans * 2 % MOD;
        return (int) ans;
    }

    class Solution {
        private final int mod=1000000007;
        public int numberOfGoodSubsets(int[] nums) {
            long res = 0;
            int[] prime = {2,3,5,7,11,13,17,19,23,29};
            // long 防止溢出
            long[] dp = new long[1024];
            // dp数组初始化
            dp[0] = 1;
            int[] count = new int[31];
            for(int num : nums) count[num]++;
            // 遍历nums中的每一个数，除了1
            for(int num = 2; num <= 30; ++num){
                // 当前数不存在，当前数带有平方数 跳过
                if(count[num] == 0 || num % 4 == 0||num % 9 == 0 || num % 25 == 0) continue;

                // 对10个质数做处理，如果当前数能被质数整除，则记录进maskForNum
                int maskForNum = 0;
                for(int i = 0; i < 10; ++i){
                    if(num % prime[i] == 0) maskForNum |= ( 1 << i);
                }

                // 遍历每一种状态
                for(int state = 0; state < 1024; ++state){
                    // maskForNum中已经存在了其中一个质数，跳过
                    if((maskForNum & state) > 0) continue;
                    //这里可能会溢出，所以dp数组类型为long
                    // 更新当前状态的的好子集个数
                    dp[maskForNum|state] = (dp[maskForNum|state] + count[num] * dp[state])%mod;
                    // System.out.println( num + " --- " + state + " --- " + dp[state]);
                }
            }
            // dp[0]不算进去
            for(int i = 1; i < 1024; ++i) res = (res + dp[i]) % mod;
            // 有多少个1，最后的结果就乘以2的多少次方
            for(int i = 0; i < count[1]; ++i) res = (res * 2) % mod;
            return (int)res;
        }
    }

//    作者：livorth-u
//    链接：https://leetcode-cn.com/problems/the-number-of-good-subsets/solution/1994-hao-zi-ji-de-shu-mu-zhuang-tai-ya-s-ln8n/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
