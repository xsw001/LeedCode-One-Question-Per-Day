package Tiger2022.offer.Tencent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        // 0 attack 1 defend
        int[] attack = new int[n + 1];
        int[] defend = new int[n + 1];
        int a = 0, d = 0;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '0') {
                attack[i] = a + i;
                a = attack[i];
                defend[i] = defend[i - 1];
            } else {
                defend[i] = d + i;
                d = defend[i];
                attack[i] = attack[i - 1];
            }
        }
        int ans = Math.min(defend[n], attack[n]);
        for (int i = 1; i <= n; i++) {
            int one = attack[i];
            int two = defend[n] - defend[i];
            ans = Math.min(ans, Math.abs(one - two));
        }
        System.out.println(ans);
    }
}

