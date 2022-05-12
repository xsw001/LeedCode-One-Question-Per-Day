package xsw.October;
/*

 */

import java.util.ArrayList;
import java.util.Arrays;

public class 最小操作次数使数组元素相等_453 {

    // 这个规律  找的也是不容易啊，花了一个小时
    public static int minMoves(int[] nums) {
        Arrays.sort(nums);
        int l = nums.length - 1;
        int ans = 0;
        int min = nums[0], max = nums[l];
        while (min < max) {
            ans += max - min;
            max = max - min + nums[l-1];
            min = nums[l];
            --l;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {1,2,6,498};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(minMoves(data));

    }

    /*
    思路和算法

因为只需要找出让数组所有元素相等的最小操作次数，所以我们不需要考虑数组中各个元素的绝对大小，
即不需要真正算出数组中所有元素相等时的元素值，只需要考虑数组中元素相对大小的变化即可。

因此，每次操作既可以理解为使 n−1 个元素增加 1，也可以理解使 1 个元素减少 1。显然，后者更利于我们的计算。

于是，要计算让数组中所有元素相等的操作数，我们只需要计算将数组中所有元素都减少到数组中元素最小值所需的操作数
     */
    class Solution {// 逆向思维啊，又没想到
        public int minMoves(int[] nums) {
            int minNum = Arrays.stream(nums).min().getAsInt();
            int res = 0;
            for (int num : nums) {
                res += num - minNum;
            }
            return res;
        }
    }

//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/solution/zui-xiao-cao-zuo-ci-shu-shi-shu-zu-yuan-3meg3/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}