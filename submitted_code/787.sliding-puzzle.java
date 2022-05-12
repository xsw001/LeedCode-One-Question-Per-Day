//
// @lc app=leetcode.cn id=787 lang=java
//
// [787] sliding-puzzle
//
class Solution {
    public int slidingPuzzle(int[][] board) {
        StringBuilder s = new StringBuilder();
        for (int[] ints : board) {
            for (int i : ints) {
                s.append(i);
            }
        }
        if (s.toString().equals("123450"))
            return 0;
        LinkedList<String> list = new LinkedList<>();
        list.add(s.toString());
        HashSet<String> visited = new HashSet<>();
        visited.add(s.toString());
        int ans = 0;
        while (!list.isEmpty()) {
            int size = list.size();
            ++ans;
            for (int i = 0; i < size; i++) {
                String cur = list.poll();
                for (String next : help(cur)) {
                    if (next.equals("123450"))
                        return ans;
                    if (!visited.contains(next)) {
                        list.add(next);
                        visited.add(next);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> help(String cur) {
        ArrayList<String> list = new ArrayList<>();
        HashMap<Integer, int[]> map = new HashMap<>();
        map.put(0, new int[]{1,3});
        map.put(1, new int[]{0,2, 4});
        map.put(2, new int[]{1,5});
        map.put(3, new int[]{0,4});
        map.put(4, new int[]{1,3,5});
        map.put(5, new int[]{2,4});
        int index = cur.indexOf('0');
        for (int i : map.get(index)) {
            char[] chars = cur.toCharArray();
            char temp = chars[i];
            chars[i] = chars[index];
            chars[index] = temp;
            list.add(new String(chars));
        }
        return list;
    }
}
// @lc code=end