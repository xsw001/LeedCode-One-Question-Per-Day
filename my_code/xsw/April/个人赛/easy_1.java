package xsw.April.个人赛;
/*
1. 采购方案
通过的用户数0
尝试过的用户数1
用户总通过次数0
用户总提交次数1
题目难度Easy
小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。

注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1

示例 1：

输入：nums = [2,5,3,5], target = 6

输出：1

解释：预算内仅能购买 nums[0] 与 nums[2]。

示例 2：

输入：nums = [2,2,1,9], target = 10

输出：4

解释：符合预算的采购方案如下：
nums[0] + nums[1] = 4
nums[0] + nums[2] = 3
nums[1] + nums[2] = 3
nums[2] + nums[3] = 10

提示：

2 <= nums.length <= 10^5
1 <= nums[i], target <= 10^5
 */

import java.util.Arrays;

public class easy_1 {

    public static int purchasePlans(int[] nums, int target) {
        int l = nums.length;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < l; i++) {
            if (nums[i] >= target)
                break;
            for (int j = i + 1; j < l; j++) {
                if (nums[j] >= target)
                    break;
                if (nums[i] + nums[j] <= target)
                    ++res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {2, 2, 1, 9};
        System.out.println(purchasePlans(data, 10));
    }


    public static int purchasePlans1(int[] nums, int target) {
        Arrays.sort(nums);
        int j = nums.length - 1;
        long res = 0;
        int mod = (int) (1e9 + 7);
        for (int i = 0; i < j; i++) {
            while (j > i && nums[i] + nums[j] > target) {
                j--;
            }
            res = (res + j - i) % mod;
        }
        return (int) res % mod;
    }
}