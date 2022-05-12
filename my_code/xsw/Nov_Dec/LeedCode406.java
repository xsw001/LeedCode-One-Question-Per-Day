package xsw.Nov_Dec;/*
406. 根据身高重建队列
假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。
编写一个算法来重建这个队列。

注意：
总人数少于1100人。

示例
    输入:
    [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

    输出:
    [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LeedCode406 {

    public static void main(String[] args) {
        //int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] people = {{6, 0}, {4, 0}, {3, 2}, {5, 0}, {2, 2}, {1, 4}};
        int[][] queue = reconstructQueue2(people);
        System.out.println(Arrays.deepToString(queue));

    }

    //就是不对   思路有问题   太复杂了
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] - o2[0]);
        System.out.println(Arrays.deepToString(people));
        int len = people.length;
        int[][] res = new int[len][];
        int i = 0;
        for (int[] result : people) {
            if (result[1] == 0) {
                res[i++] = result;
            }
        }
        int[][] rest = new int[len - i][];
        int j = 0;
        for (int[] person : people) {
            if (person[1] != 0) {
                rest[j++] = person;
            }
        }
        System.out.println(Arrays.deepToString(rest));

        for (int[] re : rest) {
            int num = 0;
            for (int[] result : res) {
                if (result != null && result[0] >= re[0])
                    ++num;
            }
            if (num == re[1])
                res[i++] = re;
            else
                res[--len] = re;
        }
        System.out.println(Arrays.deepToString(res));
        for (int r = res.length - 1; r > 0; r--) {
            int t = 0;
            int l = 0;
            for (; l < r; l++) {
                if (res[l][0] >= res[r][0])
                    ++t;
                if (t == res[r][1])
                    break;
            }
            swap(res, l, r);
            System.out.println(Arrays.deepToString(res));
        }
        return res;
    }

    public static void swap(int[][] results, int i, int j) {
        int[] temp = results[i];
        results[i] = results[j];
        results[j] = temp;
    }

    //我一上来就是先排序，该规则......奈何导航太浅，不熟悉，改不出来
    public static int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people, (int[] person1, int[] person2) -> {
                    if (person1[0] != person2[0]) {
                        return person2[0] - person1[0];
                    } else {
                        return person1[1] - person2[1];
                    }
                }

        );
        System.out.println(Arrays.deepToString(people));
        ArrayList<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            if(person[1] >= list.size())
                list.add(person);
            else
                list.add(person[1],person);
        }
        return list.toArray(new int[people.length][2]);
    }

    //
    public static int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person1[0] - person2[0];
                } else {
                    return person2[1] - person1[1];
                }
            }
        });
        System.out.println(Arrays.deepToString(people));
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < n; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}