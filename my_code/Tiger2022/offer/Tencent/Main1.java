package Tiger2022.offer.Tencent;

import org.junit.Test;

import java.util.*;

public class Main1 {

    @Test
    public void test() throws Exception {
        int[] a = {3, 1, 1, 4, 5, 6};
        System.out.println(getNumber(a));
    }

    public int getNumber(int[] a) {
        int n = a.length + 1;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i))
                set.add(i);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (set.contains(i + 1))
                list.add(a[i]);
        }
        while (list.size() != 1) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (set.contains(i + 1))
                    temp.add(list.get(i));
            }
            list = temp;
        }
        return list.get(0);
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
