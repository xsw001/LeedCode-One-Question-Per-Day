package xsw.Nov_Dec;

/*
605. 种花问题
假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。

给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。

示例 1:

输入: flowerbed = [1,0,0,0,1], n = 1
输出: True
示例 2:

输入: flowerbed = [1,0,0,0,1], n = 2
输出: False
注意:

数组内已种好的花不会违反种植规则。
输入的数组长度范围为 [1, 20000]。
n 是非负整数，且不会超过输入数组的大小。
*/

import java.util.Arrays;

public class LeedCode605 {

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int num = 0;
        int l = flowerbed.length;
        for (int i = 0; i < l; i++) {
            if(flowerbed[i]==1){
                if(i>0)
                    flowerbed[i-1]=1;
                if(i<l-1)
                    flowerbed[i+1]=1;
                ++i;
            }
        }
        for (int i = 0; i < l; i++) {
            if(flowerbed[i]==1)
                continue;
            ++num;
            ++i;
        }
        System.out.println(Arrays.toString(flowerbed));
        return num >= n;
    }

    public static void main(String[] args) {

        System.out.println(canPlaceFlowers(new int[]{0, 0, 0, 0,0, 0, 1}, 2));
    }

    //贪心
/*维护 prev 表示上一朵已经种植的花的下标位置，初始时 prev=−1，表示尚未遇到任何已经种植的花。

从左往右遍历数组 flowerbed，当遇到 flowerbed[i]=1 时根据 prev 和 i 的值计算上一个区间内可以种植花的最多数量
然后令 iprev=i，继续遍历数组 flowerbed 剩下的元素。

遍历数组 flowerbed 结束后，根据数组 prev 和长度 mm 的值计算最后一个区间内可以种植花的最多数量。

判断整个花坛内可以种入的花的最多数量是否大于或等于n。
*/
    public boolean canPlaceFlowers1(int[] flowerbed, int n) {
        int count = 0;
        int m = flowerbed.length;
        int prev = -1;
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 1) {
                if (prev < 0) {
                    count += i / 2;
                } else {
                    count += (i - prev - 2) / 2;
                }
                if (count >= n) {
                    return true;
                }
                prev = i;
            }
        }
        if (prev < 0) {
            count += (m + 1) / 2;
        } else {
            count += (m - prev - 1) / 2;
        }
        return count >= n;
    }
}
