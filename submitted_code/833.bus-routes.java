//
// @lc app=leetcode.cn id=833 lang=java
//
// [833] bus-routes
//
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
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
        for (int i = 0; i < m; i++) {
            for (int route : routes[i]) {
                ArrayList<Integer> station = stations.getOrDefault(route, new ArrayList<Integer>());
                station.add(i);
                stations.put(route, station);
            }
        }
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

// @lc code=end