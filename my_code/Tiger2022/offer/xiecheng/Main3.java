package Tiger2022.offer.xiecheng;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        PriorityQueue<Integer> one = new PriorityQueue<>();
        PriorityQueue<Integer> zero = new PriorityQueue<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0')
                zero.add(i);
            else
                one.add(i);
        }
        if (n % 2 == 1) {
            char cur = one.size() > zero.size() ? '1' : '0';
            int ans = getAns(s, one, zero, n, cur);
            System.out.println(ans);
        } else {
            int a = getAns(s, one, zero, n, '0');
            int b = getAns(s, one, zero, n, '1');
            System.out.println(Math.min(a, b));
        }
    }

    private static int getAns(String s, PriorityQueue<Integer> one, PriorityQueue<Integer> zero, int n, char cur) {
        char temp = cur;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(cur);
            cur = cur == '0' ? '1' : '0';
        }
        cur = temp;
        char[] arr = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != cur) {
                int j;
                if (arr[i] == '0') {
                    while (i < n && !one.isEmpty() && (one.peek() < i || s.charAt(one.peek()) == sb.charAt(one.peek())))
                        one.poll();
                    if(one.isEmpty())
                        continue;
                    j = one.poll();
                    reverse(arr, i, j);
                } else {
                    while (i < n && !zero.isEmpty() &&  (zero.peek() < i || s.charAt(zero.peek()) == sb.charAt(zero.peek())))
                        zero.poll();
                    if(zero.isEmpty())
                        continue;
                    j = zero.poll();
                    reverse(arr, i, j);
                }
                ans += j - i;
            }
            cur = cur == '0' ? '1' : '0';
        }
        return ans;
    }

    private static void reverse(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
/*
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
                ans += j-i;
            }
            cur = cur == '0' ? '1' : '0';
        }
        System.out.println(ans);
    }
 */