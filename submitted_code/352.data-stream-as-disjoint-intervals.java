//
// @lc app=leetcode.cn id=352 lang=java
//
// [352] data-stream-as-disjoint-intervals
//
class SummaryRanges {

        // 存储右边界
        private int[] father = new int[10001];
        // 使用有序的set存储左边界
        private Set<Integer> set = new TreeSet<>();

        public SummaryRanges() {
            // 元素值的范围为 0~10000，所以，初始化为-1
            Arrays.fill(father, -1);
        }

        public void addNum(int val) {
            if (father[val] == -1) {
                father[val] = val;
                // 将当前元素加入set中
                set.add(val);
                // 先合并右边，再合并左边
                union(val, val + 1);
                union(val - 1, val);
            }
        }

        private int find(int x) {
            if (father[x] != x)
                return father[x] = find(father[x]);
            return father[x];
        }

        private void union(int x, int y) {
            if (x >= 0 && x <= 10000 && father[x] != -1 && father[y] != -1) {
                int fx = find(x);
                int fy = find(y);
                if (fx != fy) {
                    father[fx] = fy;
                    // 可以合并，set中删除右边的那个数
                    set.remove(y);
                }
            }
        }

        public int[][] getIntervals() {
            List<int[]> list = new ArrayList<>();

            // 遍历set拿到左边界
            for (int start : set) {
                int end = find(start);
                list.add(new int[]{start, end});
            }

            return list.toArray(new int[list.size()][2]);
        }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
// @lc code=end