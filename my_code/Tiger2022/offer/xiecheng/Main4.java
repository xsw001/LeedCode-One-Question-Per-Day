package Tiger2022.offer.xiecheng;

import java.util.Arrays;
import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int len = s.length();
        double mod = 1e9 + 7;
        int[][] arr = new int[len + 1][9];
        for (int i = 1; i <= len; i++) {
            int j = (s.charAt(i-1) - '0') % 9;
            for (int k = 0; k < 9; k++) {
                arr[i][k] = (int) ((arr[i - 1][k] + arr[i - 1][(k - j + 9) % 9]) % mod);
            }
            arr[i][j] = (int) ((arr[i][j] + 1) % mod);
        }
        //System.out.println(Arrays.deepToString(arr));
        System.out.println(arr[len][0]);
    }
}
