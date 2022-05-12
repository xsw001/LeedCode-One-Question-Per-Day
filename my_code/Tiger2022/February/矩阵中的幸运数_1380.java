package Tiger2022.February;
/*
1380. 矩阵中的幸运数
给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。

幸运数是指矩阵中满足同时下列两个条件的元素：

在同一行的所有元素中最小
在同一列的所有元素中最大


示例 1：

输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
输出：[15]
解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
示例 2：

输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
输出：[12]
解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
示例 3：

输入：matrix = [[7,8],[1,2]]
输出：[7]


提示：

m == mat.length
n == mat[i].length
1 <= n, m <= 50
1 <= matrix[i][j] <= 10^5
矩阵中的所有元素都是不同的
通过次数35,894提交次数46,630
 */

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class 矩阵中的幸运数_1380 {

    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();

        for (int[] ints : matrix) {
            int min = 100001, index = -1;
            for (int j = 0; j < n; j++) {
                if (ints[j] < min) {
                    min = ints[j];
                    index = j;
                }
            }
            if (visited.contains(index))
                continue;
            int k = 0;
            while (k < m && matrix[k][index] <= min) ++k;
            if (k == m) {
                ans.add(min);
                visited.add(index);
            }
        }
        return ans;
    }

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 1;
    private static final Long KEEP_ALIVE_TIME = 1L;

    @Test
    public void test() throws Exception {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 20; i++) {
            Runnable runnable = new MyRunnable("" + i);
            executor.execute(runnable);
            System.out.println(i);
        }
        executor.shutdown();
        while(!executor.isTerminated());
        System.out.println("Finished all threads!");
    }

    public class MyRunnable implements Runnable {

        String command;

        public MyRunnable(String s) {
            this.command = s;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "start. Time = " + new Date());
            processCommand();
            System.out.println(Thread.currentThread().getName() + "end. Time = " + new Date());
        }

        private void processCommand() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "MyRunnable{" +
                    "command='" + command + '\'' +
                    '}';
        }
    }
}
