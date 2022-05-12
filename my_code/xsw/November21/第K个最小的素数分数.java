package xsw.November21;
/*

 */

import org.junit.Test;

import java.util.*;

public class 第K个最小的素数分数 {

    public int[] kthSmallestPrimeFraction1(int[] arr, int k) {
        ArrayList<Double> list = new ArrayList<>();
        HashMap<Double, int[]> map = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double t = (double) arr[i] / arr[j];
                list.add(t);
                map.put(t, new int[]{arr[i], arr[j]});
            }
        }
        Collections.sort(list);
        return map.get(list.get(k - 1));
    }

    @Test
    public void test() {
        int[] data = {1, 2, 3, 5};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(data, 3)));
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        if (n == 1)
            return arr;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] * o2[1] - o2[0] * o1[1]);
        for (int i = 1; i < n; i++) {
            queue.add(new int[]{1, arr[i]});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] poll = queue.poll();
            int l = 0, r = n - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (arr[m] <= poll[0])
                    l = m + 1;
                else
                    r = m;
            }
            if (arr[l] < poll[1])
                queue.add(new int[]{arr[l], poll[1]});
        }
        return queue.poll();
    }

    public int[] kthSmallestPrimeFraction2(int[] arr, int k) {
        int n = arr.length;
        if (n == 1)
            return arr;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> arr[o1[0]] * arr[o2[1]] - arr[o2[0]] * arr[o1[1]]);
        for (int i = 1; i < n; i++) {
            queue.add(new int[]{0, i});
        }
        for (int i = 1; i < k; i++) {
            int[] poll = queue.poll();
            if (poll[0] + 1 < poll[1])
                queue.add(new int[]{poll[0] + 1, poll[1]});
        }
        return new int[]{arr[queue.peek()[0]],arr[queue.peek()[1]]};
    }
}