package xsw.January;
/*
665. 非递减数列
给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。

我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。



示例 1:

输入: nums = [4,2,3]
输出: true
解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
示例 2:

输入: nums = [4,2,1]
输出: false
解释: 你不能在只改变一个元素的情况下将其变为非递减数列。


说明：

1 <= n <= 10 ^ 4
- 10 ^ 5 <= nums[i] <= 10 ^ 5
 */

public class LeedCode665 {

    /*当 nums[i] 破坏了数组的单调递增时，即 nums[i] < nums[i - 1]  时，为了让数组有序,我们发现一个规律：

    当 i = 1 ，那么修改 num[i- 1] ，不要动 nums[i] 
    因为nums[i]后面的元素是啥我们还不知道呢，少动它为妙。
        当 i > 1 时，我们应该优先考虑把 nums[i - 1] 调小到 >= nums[i - 2] 并且 <= nums[i]
        同样尽量不去修改 nums[i] ，理由同上。
            当 i > 1 且 nums[i] < nums[i - 2] 时，我们无法调整 nums[i - 1] 
            我们只能调整 nums[i] 到 nums[i - 1] 。
*/
    public static boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (i == 1 || nums[i] >= nums[i - 2]) {
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
                count ++;
            }
        }
        return count <= 1;
    }

    public static void main(String[] args) {
        int[] nums = {1,4,2,5};
        System.out.println(checkPossibility(nums));
    }

    public boolean checkPossibility1(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }

    /*
    	4，2，3
	-1，4，2，3
	2，3，3，2，4
        我们通过分析上面三个例子可以发现，当我们发现后面的数字小于前面的数字产生冲突后，
        [1]有时候需要修改前面较大的数字(比如前两个例子需要修改4)，
        [2]有时候却要修改后面较小的那个数字(比如前第三个例子需要修改2)，
        那么有什么内在规律吗？是有的，判断修改那个数字其实跟再前面一个数的大小有关系，
        首先如果再前面的数不存在，比如例子1，4前面没有数字了，我们直接修改前面的数字为当前的数字2即可。
        而当再前面的数字存在，并且小于当前数时，比如例子2，-1小于2，我们还是需要修改前面的数字4为当前数字2；
        如果再前面的数大于当前数，比如例子3，3大于2，我们需要修改当前数2为前面的数3。
    */
    public boolean checkPossibility2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        int cnt = 0;
        for (int i = 1; i < nums.length && cnt < 2; i++) {
            if (nums[i-1] <= nums[i]) {
                continue;
            }
            cnt++;
            if (i-2>=0 /*再前面的数存在*/&& nums[i-2] > nums[i]/*再前面的数大于当前数*/) {
                nums[i] = nums[i-1];
            }else {
                nums[i-1] = nums[i];
            }
        }
        return cnt <= 1;
    }
}