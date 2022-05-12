package Tiger2022.offer.xiecheng;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < 3 * n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < 2 * n; j++) {
                System.out.print(".");
            }
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        int point = 1;
        while (point <= n) {
            for (int i = 0; i < point; i++) {
                System.out.print(".");
            }
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            for (int i = 0; i < (n - point) * 2; i++) {
                System.out.print(".");
            }
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            for (int i = 0; i < point; i++) {
                System.out.print(".");
            }
            System.out.println();
            ++point;
        }
    }
}
