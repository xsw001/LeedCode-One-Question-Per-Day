package Tiger2022.offer.Tesla;

import java.math.BigDecimal;
import java.util.HashMap;

public class Main2 {

    public static void main(String[] args) {
        int[] a = {0, 5, 4, 4, 5, 12};
        int[] aa = {4, 2, 2, 4, 2, 2, 2, 2, 2, 2, 26, 5, 9, 5, 6, 9, 8, 8, 87, 987, 7, 7, 7, 7, 8, 8, 9, 9, 1, 9, 4, 1, 1,  1, 1, 1};
        System.out.println(solution(a));
        System.out.println(solution(aa));
    }

    public static int solution(int[] A) {
        int len = A.length;
        if (len == 1)
            return 1;
        int max = 2;
        HashMap<Integer, Integer> count = new HashMap<>();
        int l = 0, r = 0;
        while (r < len) {
            while (r < len && count.size() <= 2) {
                count.put(A[r], count.getOrDefault(A[r], 0) + 1);
                ++r;
            }
            if (count.size() == 2)
                max = Math.max(max, r - l);
            else
                max = Math.max(max, r - l - 1);
            while (l < r && count.size() > 2) {
                if (count.get(A[l]) == 1) {
                    count.remove(A[l]);
                    ++l;
                    break;
                }
                count.put(A[l], count.getOrDefault(A[l], 0) - 1);
                ++l;
            }
        }
        if (count.size() == 2)
            max = Math.max(max, r - l);
        else
            max = Math.max(max, r - l - 1);
        return max;
    }
}
