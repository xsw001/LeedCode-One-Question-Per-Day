package xsw.April.团队赛;
/*1. 蓄水
通过的用户数0
尝试过的用户数2
用户总通过次数0
用户总提交次数3
题目难度Easy
给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 i 个水缸配备的水桶容量记作 bucket[i]。小扣有以下两种操作：

升级水桶：选择任意一个水桶，使其容量增加为 bucket[i]+1
蓄水：将全部水桶接满水，倒入各自对应的水缸
每个水缸对应最低蓄水量记作 vat[i]，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。

注意：实际蓄水量 达到或超过 最低蓄水量，即完成蓄水要求。

示例 1：

输入：bucket = [1,3], vat = [6,8]

输出：4

解释：
第 1 次操作升级 bucket[1]；
第 2 ~ 4 次操作均选择蓄水，即可完成蓄水要求。
vat1.gif

示例 2：

输入：bucket = [9,0,1], vat = [0,2,2]

输出：3

解释：
第 1 次操作均选择升级 bucket[1]
第 2~3 次操作选择蓄水，即可完成蓄水要求。

提示：

1 <= bucket.length == vat.length <= 100
0 <= bucket[i], vat[i] <= 10^4

 */

import java.util.ArrayList;
import java.util.TreeMap;

public class easy_1 {

    //29 / 37 个通过测试用例
    public static int storeWater1(int[] bucket, int[] vat) {
        int maxVat = 0;
        for (int i : vat)
            maxVat = Math.max(maxVat, i);
        if (maxVat == 0)
            return 0;
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        for (int i = 0; i < bucket.length; i++) {
            if (map.containsKey(bucket[i])) {
                ArrayList<Integer> list = map.get(bucket[i]);
                list.add(vat[i]);
                map.put(bucket[i], list);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(vat[i]);
                map.put(bucket[i], list);
            }
        }
        int end = count(map);
        int res = end;
        for (int i = 0; i < end; i++) {
            for (Integer key : map.keySet()) {
                ArrayList<Integer> list = map.get(key);
                if (map.containsKey(key + 1)) {
                    ArrayList<Integer> values = map.get(key + 1);
                    values.addAll(list);
                    map.put(key + 1, values);
                } else {
                    map.put(key + 1, list);
                }
                map.remove(key);
                break;
            }
            int temp = count(map) + 1 + i;
            if (res >= temp)
                res = temp;
            else
                break;
        }

        return res;
    }

    public static int count(TreeMap<Integer, ArrayList<Integer>> map) {
        int res = 0;
        for (Integer key : map.keySet()) {
            if (key == 0) {
                res = Integer.MAX_VALUE;
                return res;
            }
            ArrayList<Integer> list = map.get(key);
            for (Integer value : list) {
                res = Math.max(res, value % key == 0 ? value / key : value / key + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] bucket = {1, 3}, vat = {9, 10};
        System.out.println(storeWater(bucket, vat));
    }

    public static int storeWater(int[] bucket, int[] vat) {
        int max = 0;
        for (int v : vat)
            if (max < v) max = v;
        if (max == 0) return 0;
        int n = bucket.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= max; i++) {//遍历倒水次数
            int per = 0;
            int cur = i;//倒水i次，所以操作次数+i
            for (int j = 0; j < n; j++) {//遍历每个水缸
                per = (vat[j] + i - 1) / i;// 水槽容量/倒水次数=每次倒水量
//+（i - 1）目的是为了向上取整(除完后如果有余数，加上i-1之后就一定会多商1，从而达到向上取整的功能)
//使用vat[j]%i==0 ? vat[j]/i : vat[j]/i+1 代替也行，但是更慢
                cur += Math.max(0, per - bucket[j]);// 每次倒水量-初始水量=需要升级次数
            }
            ans = Math.min(ans, cur);//所有倒水次数中，取最小的操作次数
        }
        return ans;
    }
}