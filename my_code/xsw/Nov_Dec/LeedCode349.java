package xsw.Nov_Dec;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
349. 两个数组的交集
给定两个数组，编写一个函数来计算它们的交集。



示例 1：

输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2]
示例 2：

输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[9,4]

*/
public class LeedCode349 {

    public static void main(String[] args) {
        int[] num1 = {1,2,2,1};
        int[] num2 = {2,2};
        int[] ints = intersection(num1, num2);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        Set<Integer> list = new HashSet<>();
        for (int i : nums2) {
            if(set.contains(i)){
                list.add(i);
            }
        }
        Integer[] array = list.toArray(new Integer[0]);
        int[] res = new int[array.length];
        int loc = 0;
        for (Integer i : array) {
            res[loc++] = i;
        }
        return res;
    }
}

