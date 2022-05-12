package Tiger2022.offer;

import java.util.ArrayList;
import java.util.Scanner;

public class 蓝桥杯热身五_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(" ");
        String s = split[0];
        int n = Integer.parseInt(split[1]);
        String[][] arr = new String[n][n];
        String[][] ans = new String[n][n];
        int l = n - 1;
        ArrayList<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(sc.nextLine());
            list.add(sb);
            for (int j = 0; j < sb.length(); j++) {
                arr[i][j] = String.valueOf(sb.charAt(j));
            }
            StringBuilder ss = sb.reverse();
            for (int j = 0; j < ss.length(); j++) {
                if (!arr[i][n - j - 1].equals(" "))
                    ans[l][j] = s;
                else
                    ans[l][j] = " ";
            }
            --l;
        }
        boolean f = false;
        int a = 0, b = n - 1;
        while (a <= b) {
            if (!list.get(a++).toString().equals(list.get(b--).toString())) {
                f = true;
                break;
            }
        }
        if (!f)
            System.out.println("bu yong dao le");
        for (int i = 0; i < n; i++) {
            StringBuilder t = new StringBuilder();
            for (int j = 0; j < n; j++) {
                t.append(ans[i][j]);
            }
            if (t.length() > 0)
                System.out.println(t.toString());
        }
    }
}
