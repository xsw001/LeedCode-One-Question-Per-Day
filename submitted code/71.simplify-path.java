//
// @lc app=leetcode.cn id=71 lang=java
//
// [71] simplify-path
//
class Solution {
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        String[] split = path.split("/");
        for (String s : split) {
            if (s.equals(".") || s.length() == 0)
                continue;
            if (s.equals("..")) {
                if (!deque.isEmpty())
                    deque.pollFirst();
            } else
                deque.addFirst(s);
        }
        if (deque.isEmpty())
            return "/";
        StringBuilder sb = new StringBuilder("/");
        while (!deque.isEmpty()) {
            sb.append(deque.pollLast()).append("/");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
// @lc code=end