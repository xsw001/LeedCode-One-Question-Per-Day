package Tiger2022.offer.wangyi;

import java.util.PriorityQueue;
import java.util.Scanner;

/*

 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int[][] dp = new int[n][m];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
            if (arr[i][0] != arr[i - 1][0])
                dp[i][0]++;
        }
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
            if (arr[0][i] != arr[0][i - 1])
                dp[0][i]++;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int a = dp[i - 1][j];
                int b = dp[i][j - 1];
                if (arr[i][j] != arr[i - 1][j])
                    a++;
                if (arr[i][j] != arr[i][j - 1])
                    b++;
                dp[i][j] = Math.min(a, b) + 1;
            }
        }
        System.out.println(dp[n - 1][m - 1]);
    }
}
