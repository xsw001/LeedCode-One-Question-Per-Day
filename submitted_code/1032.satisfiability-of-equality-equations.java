//
// @lc app=leetcode.cn id=1032 lang=java
//
// [1032] satisfiability-of-equality-equations
//
class Solution {

    private int[] parent = new int[26];

    public boolean equationsPossible(String[] equations) {
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        int l = equations.length;
        for (String equation : equations) {
            char[] array = equation.toCharArray();
            if (array[1] == '=') {
                int x = array[0] - 'a';
                int y = array[3] - 'a';
                union(x, y);
            }
        }
        for (String equation : equations) {
            char[] array = equation.toCharArray();
            if (array[1] == '!') {
                int x = array[0] - 'a';
                int y = array[3] - 'a';
                int pX = find(x);
                int pY = find(y);
                if(pX == pY)
                    return false;
            }
        }
        return true;
    }
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;
        parent[rootX] = rootY;
    }

    public int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
// @lc code=end