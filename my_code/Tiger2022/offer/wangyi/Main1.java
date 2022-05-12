package Tiger2022.offer.wangyi;

import java.util.Scanner;

/*

 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        long ans = 0;
        int n = s.length();
        int l = 0, r = 1;
        while (r < n) {
            int temp = 0;
            while (r < n && Math.abs(s.charAt(r) - s.charAt(r - 1)) < 2) {
                temp += (s.charAt(r - 1) - 'a' + 1);
                ++r;
            }
            temp += (s.charAt(r - 1) - 'a' + 1);
            int window = r - l;
            if (window > 1) {
                if (window % 2 == 0)
                    ans += temp;
                else {
                    int aa = 0;
                    for (int i = 0; i < window; i += 2) {
                        aa = Math.max(aa, temp - (s.charAt(i + l) - 'a') - 1);
                    }
                    ans += aa;
                }
            }
            l = r;
            r++;
        }
        System.out.println(ans);
    }
}
