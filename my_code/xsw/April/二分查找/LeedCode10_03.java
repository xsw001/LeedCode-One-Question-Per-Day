package xsw.April.二分查找;
/*
面试题 10.03. 搜索旋转数组
搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。

示例1:

 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 输出: 8（元素5在该数组中的索引）
示例2:

 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 输出：-1 （没有找到）
提示:

arr 长度范围在[1, 1000000]之间
通过次数8,617提交次数21,538
 */

public class LeedCode10_03 {

    public static int search(int[] nums,int target) {
        if (nums == null || nums.length == 0) {
            //  特殊情况判断
            return -1;
        }

        //  声明左索引
        int start = 0;
        //  声明右索引
        int end = nums.length - 1;

        while (start <= end) {

            if (nums[start] == target) {
                //  1. 找到结果, 因为找的是最小的索引, 所以当start符合时直接返回
                return start;
            }

            //  计算中间位置的索引
            int mid = (start + end) / 2;

            if (nums[mid] == target) {
                //  2. mid的值是目标的值, 更新右边界
                end = mid;
            } else if (nums[mid] > nums[start]) {
                //  3.1 左边是升序的
                if (nums[start] <= target && target <= nums[mid]) {
                    //  去掉右边的数据
                    end = mid;
                } else {
                    //  去掉左边的数据
                    start = mid + 1;
                }
            } else if (nums[mid] < nums[start]) {
                //  3.2 右边是升序的
                if (nums[mid] <= target && target <= nums[end]) {
                    //  去掉左边的数据
                    start = mid;
                } else {
                    //  去掉右边的数据
                    end = mid - 1;
                }
            } else {
                //  左边索引前移
                start++;
            }
        }

        //  没找到目标
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(data,5));
    }

}