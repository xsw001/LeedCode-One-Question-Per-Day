package xsw.May;

public class 二分的奥秘 {

    public static void binarySearch(int[] data, int k) {
        int l = 0, r = data.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (data[mid] >= k)
                r = mid;
            else
                l = mid + 1;
        }
        System.out.println("*大于等于：" + k + " 下标：" + l + " 值：" + data[l]);
        l = 0;
        r = data.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (data[mid] >= k)
                r = mid - 1;
            else
                l = mid;
        }
        System.out.println(" 小于：" + k + " 下标：" + l + " 值：" + data[l]);
        l = 0;
        r = data.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (data[mid] <= k)
                l = mid;
            else
                r = mid - 1;
        }
        System.out.println("*小于等于：" + k + " 下标：" + l + " 值：" + data[l]);
        l = 0;
        r = data.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (data[mid] <= k)
                l = mid + 1;
            else
                r = mid;
        }
        System.out.println(" 大于：" + k + " 下标：" + l + " 值：" + data[l]);
        System.out.println("---------------------结论--------------------------");
        System.out.println("不用在意递增递减，只要是二段性就可。重点是要找的点在边界上");
        System.out.println("找左部分的右边界，使用 r = mid - 1 ,主要靠右边移动。向上取整");
        System.out.println("找右部分的左边界，使用 l = mid + 1 ,主要靠左边移动。向下取整");
        // 找左边的右边界： 33、34、69、74、275、5751
        // 找右边的左边界： 33、34、35、74、153、278、1482
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        binarySearch(data, 15);
    }
}
