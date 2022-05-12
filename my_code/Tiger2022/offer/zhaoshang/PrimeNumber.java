package Tiger2022.offer.zhaoshang;

import javax.swing.*;
import java.util.Scanner;

public class PrimeNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter numbers (A negative number to exit): ");
        int number = 0;
        while (true) {
            number = scanner.nextInt();
            if (number < 0)
                break;
            if (number < 2) {
                System.out.println("The number is not a prime number");
                continue;
            }
            boolean f = true;
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    System.out.println("The number is not a prime number");
                    f = false;
                    break;
                }
            }
            if (f)
                System.out.println("The number is a prime number");
        }
    }

}
