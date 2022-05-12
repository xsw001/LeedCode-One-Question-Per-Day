package Tiger2022.February;
/*
917. 仅仅反转字母
给你一个字符串 s ，根据下述规则反转字符串：

所有非英文字母保留在原有位置。
所有英文字母（小写或大写）位置反转。
返回反转后的 s 。



示例 1：

输入：s = "ab-cd"
输出："dc-ba"
示例 2：

输入：s = "a-bC-dEf-ghIj"
输出："j-Ih-gfE-dCba"
示例 3：

输入：s = "Test1ng-Leet=code-Q!"
输出："Qedo1ct-eeLg=ntse-T!"


提示

1 <= s.length <= 100
s 仅由 ASCII 值在范围 [33, 122] 的字符组成
s 不含 '\"' 或 '\\'
通过次数37,274提交次数63,981
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 仅仅反转字母_917 {

    public String reverseOnlyLetters(String s) {
        char[] array = s.toCharArray();
        int l = 0, r = array.length - 1;
        while (l < r) {
            while (l < r && !Character.isLetter(array[l])) ++l;
            while (l < r && !Character.isLetter(array[r])) --r;
            char t = array[l];
            array[l++] = array[r];
            array[r--] = t;
        }
        return new String(array);
    }

    @Test
    public void test() throws Exception {

        int[] data = {1};

        List<Integer> list = new ArrayList<>();
        System.out.println(missingNumber(data));
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            pool.execute(() -> {
                try {
                    queue.put(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(123);
        for (int i = 0; i < 80; i++) {
            pool.execute(() -> {
                try {
                    Integer take = queue.take();
                    System.out.println("take -> " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(789);
    }

    public int missingNumber(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] == m) i = m + 1;
            else j = m - 1;
        }
        return i;
    }
}
