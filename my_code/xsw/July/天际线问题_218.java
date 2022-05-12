package xsw.July;
/*

 */

import java.util.*;

public class 天际线问题_218 {

    // 大问题，便捷情况处理不了，太麻烦了
    public static List<List<Integer>> getSkyline1(int[][] buildings) {
        Arrays.sort(buildings, (o1, o2) -> o2[2] - o1[2]);
        List<List<Integer>> ans = new ArrayList<>();
        int left = 0;
        for (int[] building : buildings) {
            left = Math.max(left, building[1]);
        }
        boolean[] visited = new boolean[left + 1];
        HashSet<Integer> high = new HashSet<>();
        for (int[] building : buildings) {
            if (high.contains(building[2]))
                continue;
            for (int i = building[0]; i <= building[1]; ++i) {
                if (!visited[i]) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(building[2]);
                    ans.add(list);
                    break;
                }
            }
            Arrays.fill(visited, building[0], building[1], true);
            high.add(building[2]);
        }
        ans.add(Arrays.asList(left, 0));
        ans.sort(Comparator.comparingInt(o -> o.get(0)));
        return ans;
    }

    public static void main(String[] args) {
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(getSkyline(buildings));
        System.out.println(getSkyline11(buildings));
    }

    // 枚举高度，好像也很麻烦
    public static List<List<Integer>> getSkyline2(int[][] buildings) {
        PriorityQueue<Integer> highs = new PriorityQueue<>((o1, o2) -> o2 - o1);
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, int[]> map = new HashMap<>();
        int left = 0;
        for (int[] building : buildings) {
            map.put(building[2], building);
            left = Math.max(left, building[1]);
        }
        boolean[] visited = new boolean[left + 1];
        highs.addAll(map.keySet());
        List<List<Integer>> ans = new ArrayList<>();
        for (Integer high : highs) {
            int[] building = map.get(high);

        }
        return ans;
    }

//    、、扫描线 + 优先队列
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);
        List<Integer> boundaries = new ArrayList<Integer>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int n = buildings.length, idx = 0;
        for (int boundary : boundaries) {
            //优先队列来优化寻找最大高度的时间,在我们从左到右枚举横坐标的过程中，实时地更新该优先队列即可。
            while (idx < n && buildings[idx][0] <= boundary) {
                // 过程中，我们首先将「包含该横坐标」的建筑加入到优先队列中
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});//因为每一座建筑的左边缘信息只被用作加入优先队列时的依据，当其加入优先队列后，我们只需要用到其高度信息（对最大高度有贡献）以及其右边缘信息（弹出优先队列的依据），因此只需要在优先队列中保存这两个元素即可。
                idx++;
            }
            // 然后不断检查优先队列的队首元素是否「包含该横坐标」，如果不「包含该横坐标」
            // 我们就将该队首元素弹出队列，直到队空或队首元素「包含该横坐标」即可
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }
            // 最后我们用变量 \textit{maxn}maxn 记录最大高度（即纵坐标的值），当优先队列为空时，maxn=0
            // 否则 maxn 即为队首元素
            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            //最后：如果当前关键点的纵坐标大小与前一个关键点的纵坐标大小相同，则说明当前关键点无效，我们跳过该关键点即可。
            if (ret.size() == 0 || maxn != ret.get(ret.size() - 1).get(1)) {
                ret.add(Arrays.asList(boundary, maxn));
            }
        }
        return ret;
    }

    /*暴力的算法：O(n) 地枚举建筑的每一个边缘作为关键点的横坐标，
    过程中我们 O(n) 地检查每一座建筑是否「包含该横坐标」，找到最大高度，即为该关键点的纵坐标*/
    public static List<List<Integer>> getSkyline11(int[][] buildings) {
        List<Integer> boundaries = new ArrayList<>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int boundary : boundaries) {
            int max = 0;
            for (int[] building : buildings) {
                if (building[0] <= boundary && building[1] > boundary)
                    max = Math.max(max, building[2]);
                if(building[0] > boundary)
                    break;
            }
/*            if (ret.size() == 0 || max != ret.get(ret.size() - 1).get(1)) {
                ret.add(Arrays.asList(boundary, max));
            }*/
            ret.add(Arrays.asList(boundary, max));
        }
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int pre = 0;
        for (List<Integer> t : ret) {
            if(t.get(1) == pre)
                continue;
            ans.add(t);
            pre = t.get(1);
        }
        return ans;
    }
}