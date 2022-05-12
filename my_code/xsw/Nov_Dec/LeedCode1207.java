package xsw.Nov_Dec;/*
1207. 独一无二的出现次数
给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。

如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LeedCode1207 {
    public static void main(String[] args) {
        int[] arr = {1, 4, 4, 4, 4, 2, 2, 1, 1, 3};
        boolean b = uniqueOccurrences(arr);
        System.out.println(b);
        System.out.println();
        uniqueOccurrences1(arr);
    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : map.keySet()) {
            list.add(map.get(i));
        }
        list.sort((o1, o2) -> o1 - o2);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    public static boolean uniqueOccurrences1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : arr) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        return map.size() == new HashSet<>(map.values()).size();
    }

}