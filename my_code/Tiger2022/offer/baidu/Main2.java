package Tiger2022.offer.baidu;

import java.util.Random;
import java.util.Scanner;

/*

 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        for (int i = 0; i < q; i++) {
            String s = sc.next();
            int a = 1, b = 1;
            int j = 1;
            int max = 'A', index = 0;
            for (; j < n; j++) {
                if (s.charAt(j) <= s.charAt(j - 1))
                    ++a;
                if (s.charAt(j) >= s.charAt(j - 1))
                    ++b;
                if (s.charAt(j) > max)
                    index = j;
            }
            if (a == n)
                System.out.println(1);
            else if (b == n)
                System.out.println(0);
            else
                System.out.println(index + 1);
        }
    }


}
