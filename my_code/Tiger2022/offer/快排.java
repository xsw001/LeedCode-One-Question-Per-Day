package Tiger2022.offer;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 快排 {

    public static void main(String[] args) {
        int[] data = {16, 8, 97, 9, 8, 4, 6, 65, 10, 0};
        quickSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));

        int[] data3 = {16, 8, 97, 9, 8, 4, 6, 65, 10, 0};
        quickSort1(data3, 0, data3.length - 1);
        System.out.println(Arrays.toString(data3));

        int[] data4 = {16, 8, 97, 9, 8, 4, 6, 65, 10, 0};
        quickSort2(data4, 0, data4.length - 1);
        System.out.println(Arrays.toString(data4));

        int[] data5 = {16, 8, 97, 9, 8, 4, 6, 65, 10, 0};
        sort(data5, 0, data5.length - 1);
        System.out.println(Arrays.toString(data5));

        int[] data1 = {16, 8, 97, 9, 8, 4, 6, 65, 10, 0};
        Arrays.sort(data1);
        System.out.println(Arrays.toString(data1));

        int[] data2 = {16, 8, 97, 9, 8, 4, 6, 65, 10, 0};
        bubbleSort(data2);
        System.out.println(Arrays.toString(data2));

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }
        System.out.println(queue);
    }

    private static void quickSort(int[] data, int s, int e) {
        if (s >= e)
            return;
        int l = s, r = e;
        int target = data[l];
        while (l < r) {
            while (l < r && data[r] >= target) --r;
            data[l] = data[r];
            while (l < r && data[l] <= target) ++l;
            data[r] = data[l];
        }
        data[l] = target;
        quickSort(data, s, l - 1);
        quickSort(data, r + 1, e);
    }

    private static void quickSort1(int[] data, int s, int e) {
        if (s >= e)
            return;
        int l = s, r = e;
        int target = data[l];
        while (l < r) {
            while (l < r && data[r] >= target) --r;
            while (l < r && data[l] <= target) ++l;
            int itarget = data[l];
            data[l] = data[r];
            data[r] = itarget;
        }
        data[s] = data[l];
        data[l] = target;
        quickSort1(data, s, l - 1);
        quickSort1(data, r + 1, e);
    }

    private static void bubbleSort(int[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int t = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = t;
                }
            }
        }
    }

    private static void quickSort2(int[] arr, int l, int r) {
        // 子数组长度为 1 时终止递归
        if (l >= r) return;
        // 哨兵划分操作（以 arr[l] 作为基准数）
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        // 递归左（右）子数组执行哨兵划分
        quickSort2(arr, l, i - 1);
        quickSort2(arr, i + 1, r);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void sort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while (array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }

    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int heapSize = nums.length;
            buildMaxHeap(nums, heapSize);
            //建堆完毕后，nums【0】为最大元素。逐个删除堆顶元素，直到删除了k-1个。
            for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
                //先将堆的最后一个元素与堆顶元素交换，由于此时堆的性质被破坏，需对此时的根节点进行向下调整操作。
                swap(nums, 0, i);
                //相当于删除堆顶元素，此时长度变为nums.length-2。即下次循环的i
                --heapSize;
                maxHeapify(nums, 0, heapSize);
            }
            return nums[0];
        }

        public void buildMaxHeap(int[] a, int heapSize) {
            //从最后一个父节点位置开始调整每一个节点的子树。数组长度为heasize，因此最后一个节点的位置为heapsize-1，所以父节点的位置为heapsize-1-1/2。
            for (int i = (heapSize - 2) / 2; i >= 0; --i) {
                maxHeapify(a, i, heapSize);
            }
        }

        public void maxHeapify(int[] a, int i, int heapSize) {      //调整当前结点和子节点的顺序。
            //left和right表示当前父节点i的两个左右子节点。
            int left = i * 2 + 1, right = i * 2 + 2, largest = i;
            //如果左子点在数组内，且比当前父节点大，则将最大值的指针指向左子点。
            if (left < heapSize && a[left] > a[largest]) {
                largest = left;
            }
            //如果右子点在数组内，且比当前父节点大，则将最大值的指针指向右子点。
            if (right < heapSize && a[right] > a[largest]) {
                largest = right;
            }
            //如果最大值的指针不是父节点，则交换父节点和当前最大值指针指向的子节点。
            if (largest != i) {
                swap(a, i, largest);
                //由于交换了父节点和子节点，因此可能对子节点的子树造成影响，所以对子节点的子树进行调整。
                maxHeapify(a, largest, heapSize);
            }
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
