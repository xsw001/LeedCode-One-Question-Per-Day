package Tiger2022.offer.baidu;

import java.util.Scanner;

/*

 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int max = 0;
        for (int i = 1; i <= k; i++) {
            int num = i * n;
            max = Math.max(reverse(num), max);
        }
        System.out.println(max);
    }

    private static int reverse(int num) {
        int ans = 0;
        while (num > 0) {
            ans = ans * 10 + num % 10;
            num /= 10;
        }
        return ans;
    }
}
