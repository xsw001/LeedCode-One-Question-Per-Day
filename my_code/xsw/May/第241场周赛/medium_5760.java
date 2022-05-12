package xsw.May.第241场周赛;
/*

 */

import java.util.ArrayList;
import java.util.HashMap;

public class medium_5760 {

    public static void main(String[] args) {
        int[] data = {1, 1, 2, 2, 2, 3};
        int[] data1 = {1, 4, 5, 2, 5, 4};
        FindSumPairs findSumPairs = new FindSumPairs(data, data1);
        findSumPairs.count(7);  // 返回 8 ; 下标对 (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) 满足 2 + 5 = 7 ，下标对 (5,1), (5,5) 满足 3 + 4 = 7
        findSumPairs.add(3, 2); // 此时 nums2 = [1,4,5,4,5,4]
        findSumPairs.count(8);  // 返回 2 ；下标对 (5,2), (5,4) 满足 3 + 5 = 8
        findSumPairs.count(4);  // 返回 1 ；下标对 (5,0) 满足 3 + 1 = 4
        findSumPairs.add(0, 1); // 此时 nums2 = [2,4,5,4,5,4]
        findSumPairs.add(1, 1); // 此时 nums2 = [2,5,5,4,5,4]
        findSumPairs.count(7);  // 返回 11 ；下标对 (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) 满足 2 + 5 = 7 ，下标对 (5,3), (5,5) 满足 3 + 4 = 7
    }

    //超内存了，就死活想不到map只存 num2 ？？？？？？
    static class FindSumPairs {

        int[] one;
        int[] two;
        HashMap<Integer, Integer> map = new HashMap<>();

        public FindSumPairs(int[] nums1, int[] nums2) {
            one = nums1;
            two = nums2;
            for (int value : nums1) {
                for (int i : nums2) {
                    int s = value + i;
                    map.put(s, map.getOrDefault(s, 0) + 1);
                }
            }
        }

        public void add(int index, int val) {
            for (int value : one) {
                int tt = value + two[index];
                if (map.get(tt) == 1)
                    map.remove(tt);
                else {
                    map.put(tt, map.get(tt) - 1);
                }
                tt += val;
                map.put(tt, map.getOrDefault(tt, 0) + 1);
            }
            two[index] += val;
        }

        public int count(int tot) {
            return map.getOrDefault(tot, 0);
        }
    }
//气死了
    static class FindSumPairs2 {

        int[] one;
        int[] two;
        HashMap<Integer, Integer> map = new HashMap<>();

        public FindSumPairs2(int[] nums1, int[] nums2) {
            one = nums1;
            two = nums2;
            for (int s : nums2) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }

        public void add(int index, int val) {

            if (map.get(two[index]) == 1)
                map.remove(two[index]);
            else {
                map.put(two[index], map.get(two[index]) - 1);
            }
            two[index] += val;
            map.put(two[index], map.getOrDefault(two[index], 0) + 1);
        }

        public int count(int tot) {
            int res = 0;
            for (int i : one) {
                int rest = tot - i;
                if(map.containsKey(rest))
                    res += map.get(rest);
            }
            return res;
        }
    }
}
