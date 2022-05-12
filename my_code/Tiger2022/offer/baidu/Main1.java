package Tiger2022.offer.baidu;

import java.util.Scanner;

/*

 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.println(help(sc.nextInt()));
        }
    }

    private static int help(int num) {
        int ans = 0;
        int sqrt = (int) Math.sqrt(num);
        for (int i = 1; i <= sqrt; i++) {
            if (num % i == 0) {
                if (isTure(i, num / i)) ++ans;
            }
        }
        return ans;
    }

    private static boolean isTure(int a, int b) {
        if(a == 1)
            return true;
        if (b % a == 0)
            return false;
        int min = 0, max = 0;
        for (int i = a; i >= 0; i--) {
            if (b % i == 0 && a % i == 0) {
                min = i;
                break;
            }
        }
        max = a * b / min;
        return min == 1 && max == a * b;
    }

}
