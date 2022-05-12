package Tiger2022.offer.xiecheng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        long ans = 0;
        String s = sc.next();
        HashMap<Integer, Integer> bb = new HashMap<>();
        HashMap<Integer, Integer> rr = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'B') {
                bb.put(arr[i], bb.getOrDefault(arr[i], 0) + 1);
            } else {
                rr.put(arr[i], rr.getOrDefault(arr[i], 0) + 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'B') {
                ans += rr.getOrDefault(arr[i],0);
            } else {
                ans += bb.getOrDefault(arr[i],0);
            }
        }
        System.out.println(ans/2);
    }
}
/*
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            map.computeIfAbsent(arr[i], k -> new ArrayList<Integer>()).add(i);
        }
        int ans = 0;
        String s = sc.next();
        HashSet<Integer> blue = new HashSet<>();
        HashSet<Integer> red = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'B') {
                for (Integer integer : map.get(arr[i])) {
                    if (red.contains(integer))
                        ++ans;
                }
                blue.add(i);
            }else {
                for (Integer integer : map.get(arr[i])) {
                    if (blue.contains(integer))
                        ++ans;
                }
                red.add(i);
            }
        }
        System.out.println(ans);
    }
 */
