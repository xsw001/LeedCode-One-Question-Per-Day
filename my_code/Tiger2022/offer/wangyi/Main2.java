package Tiger2022.offer.wangyi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

/*

 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i+=2) {
            list.add(i);
        }
        for (int i = 1; i <= n; i+=2) {
            list.add(i);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if(i < n)
                System.out.print(" ");
        }
    }
}
