package xsw.Nov_Dec;//简单题，花了一上午看，哎

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*

        给你一个数组 nums，对于其中每个元素nums[i]，请你统计数组中比它小的所有数字的数目。

        换而言之，对于每个nums[i]你必须计算出有效的j的数量，其中 j 满足j != i 且 nums[j] < nums[i]。

        以数组形式返回答案。

*/


public class LeedCode1365 {

    public static void main(String[] args) {

        int[] arr = {8, 1, 2, 2, 3, 5, 6, 18, 0, 1};
        int[] ints = smallerNumbersThanCurrent(arr);
        System.out.println(Arrays.toString(ints));

    }

    //最笨的
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            int temp = 0;
            for (int j = 0; j < nums.length; ++j) {
                if (i != j && nums[j] < nums[i])
                    ++temp;
            }
            res[i] = temp;
        }
        return res;
    }

    //最好的
    public static int[] smallerNumbersThanCurrent1(int[] nums) {
        int n = nums.length;
        int[] counts = new int[101];
        for (int i = 0; i < n; ++i) {
            counts[nums[i]] += 1;
        }
        System.out.println(Arrays.toString(counts));

        for (int i = 1; i < 101; ++i) {
            counts[i] = counts[i - 1] + counts[i];
        }
        System.out.println(Arrays.toString(counts));

        int[] res = new int[n];
        for (int i = 0; i < res.length; i++) {
            res[i] = nums[i] == 0 ? 0 : counts[nums[i] - 1];
        }
        return res;
    }

    //用的Map，我没用出来的，别人用出来了
    public static int[] smallerNumbersThanCurrent2(int[] nums) {
        //使用数组need将数组nums由小到大排列
        int[] need=new int[nums.length];
        for(int i=0;i<nums.length;i++) {
            need[i]=nums[i];
        }
        Arrays.sort(need);

        //利用HashMap记录need[i]所在的位置，即有多少个小于它的数
        Map<Integer,Integer> nd=new HashMap<>();
        for(int i=need.length-1;i>=0;i--) {
            nd.put(need[i],i);
        }

        //写入答案数组ans
        int[] ans=new int[nums.length];
        for(int i=0;i<ans.length;i++) {
            ans[i]=nd.get(nums[i]);
        }
        return ans;
    }

    // 用二维数组记录原位置，排序
    public static int[] smallerNumbersThanCurrent3(int[] nums) {
        int n = nums.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        Arrays.sort(data, (data1,data2)->data1[0] - data2[0]);

        int[] ret = new int[n];
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (prev == -1 || data[i][0] != data[i - 1][0]) {
                prev = i;
            }
            ret[data[i][1]] = prev;
        }
        return ret;
    }
}
