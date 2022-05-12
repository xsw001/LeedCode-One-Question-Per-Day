package xsw.May.第240场周赛;
/*

 */

import java.util.*;

public class easy_5750 {

    public static int maximumPopulation1(int[][] logs) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] log : logs) {
            for (int i = log[0]; i < log[1]; i++) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }
        PriorityQueue<Integer> list = new PriorityQueue<Integer>(map.keySet());
        int ans = 0, max = 0;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Integer key = list.poll();
            Integer num = map.get(key);
            if (num > max) {
                ans = key;
                max = num;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] data = {{1982,1998}, {2013,2042}, {2010,2035}, {2022,2050}, {2047,2048}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(maximumPopulation(data));
    }

    public static int maximumPopulation(int[][] logs) {
        int min = 1950;
        int[] cnt = new int[2050 + 1 - min];
        for(int[] log : logs){
            cnt[log[0] - min]++;
            cnt[log[1] - min]--;
        }
        for(int i = 1; i < cnt.length; i++){
            cnt[i] += cnt[i - 1];
        }
        int best = 0;
        for(int i = 0; i < cnt.length; i++){
            if(cnt[i] > cnt[best]){
                best = i;
            }
        }
        return best + min;
    }
}