//
// @lc app=leetcode.cn id=753 lang=java
//
// [753] open-the-lock
//
class Solution {
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000"))
            return 0;
        HashSet<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000"))
            return -1;

        LinkedList<String> list = new LinkedList<String>();
        list.add("0000");
        HashSet<String> visited = new HashSet<>();
        visited.add("0000");

        int ans = 0;
        while (!list.isEmpty()) {
            ++ans;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String cur = list.poll();
                for (String s : next(cur)) {
                    if (target.equals(s))
                        return ans;
                    if (!visited.contains(s) && !dead.contains(s)) {
                        list.add(s);
                        visited.add(s);
                    }
                }
            }
        }
        return -1;
    }

    private HashSet<String> next(String cur) {
        HashSet<String> list = new HashSet<>();
        char[] chars = cur.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            chars[i] = big(c);
            list.add(new String(chars));
            chars[i] = small(c);
            list.add(new String(chars));
            chars[i] = c;
        }
        return list;
    }

    private char small(char c) {
        return c == '9' ? '0' : (char) (c + 1);
    }

    private char big(char c) {
        return c == '0' ? '9' : (char) (c - 1);
    }
}
// @lc code=end