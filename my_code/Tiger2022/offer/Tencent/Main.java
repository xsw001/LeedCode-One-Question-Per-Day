package Tiger2022.offer.Tencent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.next());
        }
        int m = list.get(0).length();
        char[][] arr = new char[m][n];
        for (int i = 0; i < n; i++) {
            String s = list.get(i);
            for (int j = 0; j < s.length(); j++) {
                arr[j][i] = s.charAt(j);
            }
        }
        ArrayList<Long> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ans.add(Long.valueOf(new String(arr[i])));
        }
        Collections.sort(ans);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i));
            if (i < ans.size())
                System.out.print(" ");
        }
    }
}
