package Tiger2022.offer.xiecheng;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main31 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        PriorityQueue<Integer> one = new PriorityQueue<>();
        PriorityQueue<Integer> zero = new PriorityQueue<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0')
                zero.add(i);
            else
                one.add(i);
        }
        if (one.size() != zero.size()) {
            char cur = one.size() > zero.size() ? '1' : '0';
            char[] arr = s.toCharArray();
            int ans = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != cur) {
                    int j;
                    if (arr[i] == '0') {
                        while (one.peek() < i)
                            one.poll();
                        j = one.poll();
                        reverse(arr, i, j);
                    } else {
                        while (zero.peek() < i)
                            zero.poll();
                        j = zero.poll();
                        reverse(arr, i, j);
                    }
                    ans += j - i;
                }
                cur = cur == '0' ? '1' : '0';
            }
            System.out.println(ans);
        } else {
            char cur = '0';
            char[] arr = s.toCharArray();
            int a = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != cur) {
                    int j;
                    if (arr[i] == '0') {
                        while (one.peek() < i)
                            one.poll();
                        j = one.poll();
                        reverse(arr, i, j);
                    } else {
                        while (zero.peek() < i)
                            zero.poll();
                        j = zero.poll();
                        reverse(arr, i, j);
                    }
                    a += j - i;
                }
                cur = cur == '0' ? '1' : '0';
            }
            char cur1 = '0';
            char[] arr1 = s.toCharArray();
            int b = 0;
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] != cur1) {
                    int j;
                    if (arr1[i] == '0') {
                        while (one.peek() < i)
                            one.poll();
                        j = one.poll();
                        reverse(arr1, i, j);
                    } else {
                        while (zero.peek() < i)
                            zero.poll();
                        j = zero.poll();
                        reverse(arr1, i, j);
                    }
                    b += j - i;
                }
                cur1 = cur1 == '0' ? '1' : '0';
            }
            System.out.println(Math.min(a, b));
        }
    }

    private static void reverse(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
