package Tiger2022.offer.Alibaba;
/*

 */

import org.junit.Test;

import java.util.HashSet;
import java.util.Scanner;


public class e1 {

    @Test
    public void test() throws Exception {

    }


    public static class Main {
        public static void main(String[] args) {
            HashSet<String> set = new HashSet<>();
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                String s = sc.next();
                if (s.length() < 6 || s.length() > 12) {
                    System.out.println("illegal length");
                    continue;
                }
                if (set.contains(s))
                    System.out.println("acount existed");
                else {
                    boolean f = true;
                    for (int j = 0; j < s.length(); j++) {
                        if (!Character.isLetter(s.charAt(j))) {
                            System.out.println("illegal charactor");
                            f = false;
                            break;
                        }
                    }
                    if (f) {
                        System.out.println("registration complete");
                        set.add(s);
                    }
                }
            }
        }
    }
}
