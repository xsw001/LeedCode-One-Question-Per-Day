package Tiger2022.offer.Alibaba;
/*

 */

import org.junit.Test;

import java.util.*;
import java.util.Scanner;

public class e2 {

    @Test
    public void test() throws Exception {

    }

    public static class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                int[] arr = new int[5];
                for (int j = 0; j < 5; j++) {
                    arr[j] = sc.nextInt();
                }
                System.out.println(count(arr));
            }
        }

        private static int count(int[] arr) {
            Arrays.sort(arr);
            int ans = 0;
            while (arr[1] > 0) {
                int t = arr[1] - arr[0] + 1;
                ans += arr[1] - t;
                for (int i = 1; i < 5; i++) {
                    arr[i] -= t;
                }
                Arrays.sort(arr);
            }
            return ans;
        }
    }
}
