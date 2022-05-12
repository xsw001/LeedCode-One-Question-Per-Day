package Tiger2022.offer.yuewen;
/*

 */

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.*;

public class LeedCode1 {


    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        ExecutorService pool = Executors.newFixedThreadPool(6);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                5000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadPoolExecutor.CallerRunsPolicy());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (a){
                    try {
                        Thread.sleep(100);
                        synchronized (b){
                            System.out.println(111);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (b){
                    try {
                        Thread.sleep(100);
                        synchronized (a){
                            System.out.println(222);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public boolean canPartitionKSubsets (int[] nums, int k) {
        int ans = 0;
        for (int num : nums) {
            ans += num;
        }
        return ans % k == 0;
    }

}
