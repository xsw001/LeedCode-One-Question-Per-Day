package xsw.June.第246场周赛;
/*

 */

import java.util.ArrayList;

public class LeedCode1 {

    public String largestOddNumber(String num) {
        int index = num.length()-1;
        for (int i = num.length()-1; i >= 0; i--) {
            if(Integer.parseInt(num.charAt(i)+"") % 2 == 1) {
                index = i;
                break;
            }
        }
        return num.substring(0,index);
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}