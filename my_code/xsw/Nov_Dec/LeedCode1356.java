package xsw.Nov_Dec;/*
1356. 根据数字二进制下 1 的数目排序
给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。

如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。

请你返回排序后的数组。

示例 1：

    输入：arr = [0,1,2,3,4,5,6,7,8]
    输出：[0,1,2,4,8,3,5,6,7]
    解释：[0] 是唯一一个有 0 个 1 的数。
    [1,2,4,8] 都有 1 个 1 。
    [3,5,6] 有 2 个 1 。
    [7] 有 3 个 1 。
    按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
示例 2：

    输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
    输出：[1,2,4,8,16,32,64,128,256,512,1024]
    解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
*/


import java.util.*;

public class LeedCode1356 {
    public static int[] sortByBits(int[] arr) {
        Arrays.sort(arr);
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(binary(i));
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer i : set) {
            for (int j : arr) {
                if (binary(j) == i) {
                    list.add(j);
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int[] sortByBits1(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        list.sort((o1, o2) -> {
            if (binary(o1) == binary(o2))
                return o1 - o2;
            else
                return binary(o1) - binary(o2);
        });
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int[] sortByBits3(int[] arr) {
        int[] bt = new int[10001];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
            bt[i] = binary(i);
        }
        list.sort((o1, o2) -> {
            if (bt[o1] == bt[o2])
                return o1 - o2;
            else
                return bt[o1] - bt[o2];
        });
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int binary(int a) {
        int num = 0;
        while (a != 0) {
            num += a % 2;
            a = a / 2;
        }
        return num;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 2, 3, 5, 7, 11};
        int[] ints = sortByBits5(arr);
        System.out.println(Arrays.toString(ints));

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 10000; i++) {
            System.out.println(i >> 1 + (i & 1));
        }
        System.out.println(Collections.max(list));
//        System.out.println(Integer.bitCount(151));
//        System.out.println(binary(151));

    }


    public static int[] sortByBits4(int[] arr) {
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
        }
        //最牛逼的
        int[] bit = new int[10001];
        for (int i = 1; i <= 10000; ++i) {
            bit[i] = bit[i >> 1] + (i & 1);
        }
        list.sort((x, y) -> {
            if (bit[x] != bit[y]) {
                return bit[x] - bit[y];
            } else {
                return x - y;
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    //有点6，投机取巧？
    public static int[] sortByBits5(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] += Integer.bitCount(arr[i]) * 100000;
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] %= 100000;
        }
        return arr;
    }
}
