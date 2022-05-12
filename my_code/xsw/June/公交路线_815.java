package xsw.June;
/*
给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。

例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。

求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。

 

示例 1：

输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
输出：2
解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
示例 2：

输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
输出：-1
 

提示：

1 <= routes.length <= 500.
1 <= routes[i].length <= 105
routes[i] 中的所有值 互不相同
sum(routes[i].length) <= 105
0 <= routes[i][j] < 106
0 <= source, target < 106
通过次数12,111提交次数32,878

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/bus-routes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;
import java.util.stream.Collectors;

public class 公交路线_815 {

    public int numBusesToDestination1(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int s = 0;
        int t = 0;
        for (int i = 0; i < routes.length; i++) {
            boolean flag = false;
            for (int route : routes[i]) {
                if (route == source) ++s;
                if (route == target) ++t;
                if (route == source) {
                    flag = true;
                }
                ArrayList<Integer> station = map.getOrDefault(route, new ArrayList<Integer>());
                station.add(i);
                map.put(route, station);
            }
            if (flag) {
                for (int ii : routes[i]) {
                    visited.add(ii);
                }
            }
        }
        if (s == 0 || t == 0)
            return -1;
        if (visited.contains(target))
            return 1;
        LinkedList<Integer> list = new LinkedList<Integer>(visited);
        int ans = 1;
        while (!list.isEmpty()) {
            ++ans;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                for (Integer newStation : map.get(list.poll())) {
                    for (int j : routes[newStation]) {
                        if (j == target)
                            return ans;
                        if (visited.add(j))
                            list.add(j);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] data = {};
        System.out.println(numBusesToDestination(data, 0, 100000));

    }

    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        HashMap<Integer, HashSet<Integer>> routesMap = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int s = 0;
        int t = 0;
        for (int i = 0; i < routes.length; i++) {
            boolean flag = false;
            HashSet<Integer> set = routesMap.getOrDefault(i, new HashSet<Integer>());
            for (int route : routes[i]) {
                set.add(route);
                if (route == source) {
                    flag = true;
                }
                ArrayList<Integer> station = map.getOrDefault(route, new ArrayList<Integer>());
                station.add(i);
                map.put(route, station);
            }
            if (set.contains(source))
                ++s;
            if (set.contains(target))
                ++t;
            routesMap.put(i, set);
            if (flag) {
                for (int ii : routes[i]) {
                    visited.add(ii);
                }
            }
        }
        if (s == 0 || t == 0)
            return -1;
        if (visited.contains(target))
            return 1;
        LinkedList<Integer> list = new LinkedList<Integer>(visited);
        HashSet<Integer> seen = new HashSet<>();
        int ans = 1;
        while (!list.isEmpty()) {
            ++ans;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Integer poll = list.poll();
                if (seen.contains(poll))
                    continue;
                seen.add(poll);
                for (Integer newStation : map.get(poll)) {
                    HashSet<Integer> hashSet = routesMap.get(newStation);
                    if (hashSet.contains(target))
                        return ans;
                    list.addAll(hashSet);
                }
            }
        }
        return -1;
    }

    public int numBusesToDestination2(int[][] routes, int source, int target) {
        if (source == target) return 0;   //起点和终点相同，不需要乘坐公交车
        int m = routes.length;
        //记录到达过的站点，以及其最少需要乘坐多少次bus，同时避免重复遍历
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(100000) {{
            put(source, 0);
        }};
        //记录已经坐过的bus，避免重复遍历
        HashMap<Integer, Integer> accessedBus = new HashMap<>(500);
        //队列储存当前到达的站点，通过map避免向队列中加入重复的站点
        Queue<Integer> que = new LinkedList<Integer>() {{
            offer(source);
        }};
        //记录每个站点有哪些bus经过
        HashMap<Integer, ArrayList<Integer>> stations = new HashMap<>();
        int s = 0;
        int t = 0;
        for (int i = 0; i < m; i++) {
            for (int route : routes[i]) {
                if (route == source) ++s;
                if (route == target) ++t;
                ArrayList<Integer> station = stations.getOrDefault(route, new ArrayList<Integer>());
                station.add(i);
                stations.put(route, station);
            }
        }
        if (s == 0 || t == 0)
            return -1;
        while (!que.isEmpty()) {
            //cur为当前的站点，num为当前站点需要操作多少次车
            int cur = que.poll(), num = map.get(cur);
            //遍历经过当前站点的所有bus
            for (Integer bus : stations.get(cur)) {
                //如果当前的bus乘坐过，则进行下一轮循环
                if (accessedBus.containsKey(bus)) continue;
                //如果没有乘坐过，遍历当前bus的所有经过站点
                for (int nextStation : routes[bus]) {
                    //如果到达目标站点，直接返回
                    if (nextStation == target)
                        return num + 1;
                    //如果没有到达过当前站点，记录当前站点最少需要操作num+1次bus，并且将当前站点加入队列
                    if (!map.containsKey(nextStation)) {
                        map.put(nextStation, num + 1);
                        que.offer(nextStation);
                    }
                }
                //记录当前的bus已经乘坐过，避免重复遍历
                accessedBus.put(bus, 0);
            }
        }

        //BFS结束，依然没有到达目标站点，证明无法到达该站点，返回-1
        return -1;
    }
}