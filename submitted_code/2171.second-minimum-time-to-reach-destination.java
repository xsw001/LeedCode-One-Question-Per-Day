//
// @lc app=leetcode.cn id=2171 lang=java
//
// [2171] second-minimum-time-to-reach-destination
//
class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], f -> new ArrayList<Integer>()).add(edge[1]);
            map.computeIfAbsent(edge[1], f -> new ArrayList<Integer>()).add(edge[0]);
        }
        boolean[] ifvisited = new boolean[n + 1];
        int[] visitedtime = new int[n + 1];
        Arrays.fill(visitedtime, -1);
        int num = -1, total_time = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        while (!deque.isEmpty()) {
            int size = deque.size();
            total_time += time;
            for (int i = 0; i < size; i++) {
                int index = deque.pollFirst();
                ArrayList<Integer> list = map.get(index);
                for (int t : list) {
                    if (!ifvisited[t]) {
                        deque.add(t);
                        if (visitedtime[t] == -1)
                            visitedtime[t] = total_time;
                        else if (visitedtime[t] != -1 && total_time != visitedtime[t])
                            ifvisited[t] = true;
                    }
                    if (t == n) {
                        if (num == -1)
                            num = total_time;
                        else if (total_time > num)
                            return total_time;
                    }
                }
            }
            if ((total_time / change) % 2 == 1)
                total_time = (total_time / change + 1) * change;
        }
        return total_time;
    }
}
// @lc code=end