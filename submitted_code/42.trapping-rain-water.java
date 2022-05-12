//
// @lc app=leetcode.cn id=42 lang=java
//
// [42] trapping-rain-water
//
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int ans = 0;
        Deque<Integer> d = new ArrayDeque<>();
        for(int i=0;i<n;++i){
            while(!d.isEmpty() && height[i] > height[d.peekLast()]){
                int cur = d.pollLast();

                if(d.isEmpty())
                    continue;
                
                int l = d.peekLast();
                int w = i-l-1;
                int h = Math.min(height[l],height[i]) - height[cur];
                ans += w*h; 
            }
            d.addLast(i);
        }
        return ans;
    }
}
// @lc code=end