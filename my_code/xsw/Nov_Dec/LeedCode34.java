package xsw.Nov_Dec;/*
34. 在排序数组中查找元素的第一个和最后一个位置

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。
    进阶：
    你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：

输入：nums = [], target = 0
输出：[-1,-1]


提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109
*/

import java.util.Arrays;

public class LeedCode34 {
    //笨方法
    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = res[1] = -1;
        if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1])
            return res;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                res[0] = i;
                ++i;
                while (i < nums.length && nums[i] == target) {
                    ++i;
                }
                res[1] = i - 1;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {6};
        int target = 6;
        int[] range = searchRange1(nums, target);
        System.out.println(Arrays.toString(range));
    }

    //二分
    public static int[] searchRange1(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = res[1] = -1;
        if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1])
            return res;
        int l = 0, r = nums.length - 1;
        while (l<=r){
            int mid = (l+r)/2;
            if(nums[mid]<target)
                l = mid+1;
            else if(nums[mid]>target)
                r = mid-1;
            else {
                l = r = mid;
                while (r < nums.length-1 && nums[r+1] == target)
                    ++r;
                while(l>0 &&  nums[l-1] == target)
                    --l;
                res[0] = l;
                res[1] = r;
                break;
            }
        }
        return res;
    }

}
