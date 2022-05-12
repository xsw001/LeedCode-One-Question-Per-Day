package Tiger2022.offer.WeBank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0;
        while(n > 0){
            if(n % 16 > 9)
                ++ans;
            n /= 16;
        }
        System.out.println(ans);
    }
}
