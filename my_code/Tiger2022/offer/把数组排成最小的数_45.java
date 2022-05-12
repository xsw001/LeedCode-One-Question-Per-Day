package Tiger2022.offer;
/*
剑指 Offer 45. 把数组排成最小的数
输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。



示例 1:

输入: [10,2]
输出: "102"
示例 2:

输入: [3,30,34,5,9]
输出: "3033459"


提示:

0 < nums.length <= 100
说明:

输出结果可能非常大，所以你需要返回一个字符串而不是整数
拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
通过次数143,688提交次数257,867
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;

public class 把数组排成最小的数_45 {

    @Test
    public void test() throws Exception {
        int[] data = {4704, 6306, 9385, 7536, 3462, 4798, 5422, 1, 213, 2362, 3021, 2745, 3636, 6265, 1518, 8398};
        String[] array = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            array[i] = String.valueOf(data[i]);
        }
        quickSortStr(array, 0, data.length - 1);
        System.out.println(Arrays.toString(array));
        System.out.println(minNumber(data));

    }

    public String minNumber(int[] nums) {
        String[] array = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(array, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        System.out.println(Arrays.toString(array));
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s);
        }
        return sb.toString();
    }

    public void quickSortString(String[] strs, int l, int r) {
        if (l >= r)
            return;
        int i = l, j = r;
        String tmp = strs[i];
        while (i < j) {
            while ((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
            while ((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;

        quickSortString(strs, l, i - 1);
        quickSortString(strs, j + 1, r);
    }

    public void quickSortStr(String[] strs, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        String temp = strs[l];
        while (i < j) {
            while (i < j && Integer.parseInt(strs[j]) >= Integer.parseInt(temp)) --j;
            strs[i] = strs[j];
            while (i < j && Integer.parseInt(strs[i]) <= Integer.parseInt(temp)) ++i;
            strs[j] = strs[i];
        }
        strs[i] = temp;
        quickSortStr(strs, l, i - 1);
        quickSortStr(strs, j + 1, r);
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }
}
