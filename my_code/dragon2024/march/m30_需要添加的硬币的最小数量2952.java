package dragon2024.march;

import org.junit.Test;

import java.util.Arrays;

public class m30_需要添加的硬币的最小数量2952 {

    @Test
    public void test() {
        int[] arr = {1, 4, 10};
        int minimumAdded = minimumAddedCoins(arr, 9);
        System.out.println(minimumAdded);
    }

    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int count = 0;
        int maxNum = 0;
        for (int coin : coins) {
            while (maxNum + 1 < coin && maxNum < target) {
                count++;
                maxNum += maxNum + 1;
            }
            maxNum += coin;
            if (maxNum >= target) {
                break;
            }
        }
        while (maxNum < target) {
            count++;
            maxNum += maxNum + 1;
        }
        return count;
    }
}
