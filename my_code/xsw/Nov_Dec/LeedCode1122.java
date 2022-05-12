package xsw.Nov_Dec;/*
1122. 数组的相对排序
给你两个数组，arr1 和 arr2，

arr2 中的元素各不相同
arr2 中的每个元素都出现在 arr1 中
对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

示例：

    输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
    输出：[2,2,2,1,4,3,3,9,6,7,19]

提示：

    arr1.length, arr2.length <= 1000
    0 <= arr1[i], arr2[i] <= 1000
    arr2 中的元素 arr2[i] 各不相同
    arr2 中的每个元素 arr2[i] 都出现在 arr1 中
*/

import java.util.Arrays;

public class LeedCode1122 {
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int st = 0;
        for (int value : arr2) {
            for (int j = 0; j < len1; j++) {
                if (arr1[j] == value) {
                    int temp = arr1[j];
                    arr1[j] = arr1[st];
                    arr1[st] = temp;
                    ++st;
                }
            }
        }
        Arrays.sort(arr1,st,len1);
        return arr1;
    }

    public static void main(String[] args) {
        int[] arr1 = {33, 22, 48, 4, 39, 36, 41, 47, 15, 45};
        int[] arr2 = {22, 33, 48, 4};
        int[] array = relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(array));
    }
}
