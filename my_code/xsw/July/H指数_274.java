package xsw.July;
/*

 */

import java.util.ArrayList;
import java.util.*;

public class H指数_274 {

    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int size = citations.length;
        int l = 0, r = citations[size-1];
        while (l < r){
            int mid = (l+r)/2+1;
            int h = 0;
            while (mid > citations[h])
                ++h;
            if(size-h >= mid)
                l = mid;
            else
                r = mid-1;

        }
        return l;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

}