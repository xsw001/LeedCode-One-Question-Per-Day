package xsw.May.第241场周赛;
/*

 */

public class medium_5761 {

    public static void main(String[] args) {
        String s = "1110000000100001010100101010000101010101001000001110101000010111101100000111110001000111010111101100001100001001100101011110100011111100000000100011111011110111111011110111010100111101011111111101101100101010110000011110110100101111000100000001100000";
        System.out.println(s);
        System.out.println(minSwaps(s));
    }

    public static int minSwaps(String s) {
        int one = 0;
        int zero = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ++one;
            } else {
                ++zero;
            }
        }
        if (Math.abs(one - zero) > 1)
            return -1;
        if (one > zero)
            return count(s,"10");
        else if (one < zero)
            return count(s,"01");
        else
            return Math.min(count(s,"10"),count(s,"01"));
    }

    private static int count(String s, String sub) {
        String str = "";
        for (int i = 0; i < s.length() / 2; i++) {
            str += sub;
        }
        if (s.length() % 2 == 1) {
            str += sub.charAt(0) + "";
        }

        System.out.println(str);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != str.charAt(i))
                ++res;
        }
        return (res + 1) / 2;
    }

}