package Tiger2022.offer.wangyi;

import java.util.Scanner;

/*

 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        if (x <= y) {
            System.out.println(getI(Math.max(a, b), y));
        } else if (x >= y * 2) {
            int i = getI(a, x);
            int j = getI(b, x);
            System.out.println(i + j);
        } else {
            int big = Math.max(a, b);
            int small = Math.min(a, b);
            int j = getI(small, y);
            big -= j * y;
            if (big > 0)
                System.out.println(j + getI(big, x));
            else
                System.out.println(j);
        }
    }

    private static int getI(int a, int x) {
        return a % x == 0 ? a / x : a / x + 1;
    }
}
