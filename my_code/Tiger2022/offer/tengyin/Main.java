package Tiger2022.offer.tengyin;

import java.math.BigInteger;

public class Main {

    // buhui 
    public static int numsOfStrings(int n, int k) {
        if (k == 1)
            return 26;
        long ans = 26;
        for (int i = 1; i < k; i++) {
            ans = (ans * 25) % 1000000;
        }

        long res = n - 2 * k;
        long di = k * res;
        long other = 1;
        for (long l = di; l > res; l--) {
            other = (other * l) % 1000000;
        }
        return (int) (ans * other) ;
    }

    public static void main(String[] args) {
        System.out.println(numsOfStrings(50, 2));
    }
}
