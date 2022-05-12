package Tiger2022.offer.Tencent;
import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[][] dp = new int[n][2];
        dp[0][0] = m;
        dp[0][1] = m - arr[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - arr[i]);
        }
        System.out.println(dp[n - 1][0]);
    }
}
