//
// @lc app=leetcode.cn id=100281 lang=java
//
// [100281] ji-qi-ren-de-yun-dong-fan-wei-lcof
//
class Solution {
    HashSet<Integer> ans;
    int mm, nn;

    public int movingCount(int m, int n, int k) {
        ans = new HashSet<>();
        mm = m;
        nn = n;
        really(0, 0, k, new HashSet<>());
        return ans.size();
    }

    private void really(int i, int j, int k, HashSet<Integer> visted) {
        if (i < 0 || i >= mm || j < 0 || j >= nn || visted.contains(i * nn + j) || ans.contains(i * nn + j) || !can(i, j, k))
            return;
        if (can(i, j, k)) {
            ans.add(i * nn + j);
        }
        visted.add(i * nn + j);
        really(i + 1, j, k, visted);
        really(i, j + 1, k, visted);
        visted.remove(i * nn + j);
    }

    private boolean can(int i, int j, int k) {
        int t = 0;
        while (i > 0) {
            t += i % 10;
            i /= 10;
        }
        while (j > 0) {
            t += j % 10;
            j /= 10;
        }
        return t <= k;
    }
}
// @lc code=end