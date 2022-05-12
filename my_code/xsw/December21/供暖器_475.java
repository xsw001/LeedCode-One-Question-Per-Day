package xsw.December21;
/*
475. 供暖器
冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。

在加热器的加热半径范围内的每个房屋都可以获得供暖。

现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。

说明：所有供暖器都遵循你的半径标准，加热的半径也一样。



示例 1:

输入: houses = [1,2,3], heaters = [2]
输出: 1
解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
示例 2:

输入: houses = [1,2,3,4], heaters = [1,4]
输出: 1
解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
示例 3：

输入：houses = [1,5], heaters = [2]
输出：3


提示：

1 <= houses.length, heaters.length <= 3 * 104
1 <= houses[i], heaters[i] <= 109
通过次数25,887提交次数73,708
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class 供暖器_475 {

    public int findRadius1(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int radius = 0;
        for (int house : houses) {
            int l = 0, r = heaters.length - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (house < heaters[m])
                    r = m;
                else
                    l = m + 1;
            }
            if (l == 0)
                radius = Math.max(radius, Math.abs(house - heaters[l]));
            else
                radius = Math.max(radius, Math.min(Math.abs(house - heaters[l - 1]), Math.abs(heaters[l] - house)));
        }
        return radius;
    }

    @Test
    public void test() {
        int[] data = {474833169,264817709,998097157,817129560};
        int[] t = {197493099,404280278,893351816,505795335};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(findRadius1(data, t));
    }

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int l = 0, r = Math.max(houses[houses.length - 1], heaters[heaters.length - 1]);
        while (l < r) {
            int m = (l + r) / 2;
            if (can(houses, heaters, m))
                r = m;
            else
                l = m + 1;
        }
        return l;
    }

    private boolean can(int[] houses, int[] heaters, int r) {
        int i = 0;
        for (int heater : heaters) {
            int start = heater - r, end = heater + r;
            while (i < houses.length && houses[i] >= start && houses[i] <= end)
                ++i;
            if (i == houses.length)
                return true;
        }
        return false;
    }
}