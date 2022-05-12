//
// @lc app=leetcode.cn id=100278 lang=java
//
// [100278] xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
//
class Solution {
    public int minArray(int[] numbers) {
        int l = 0, r = numbers.length-1;
        while(l < r){
            int m = (l+r)/2;
            if(numbers[m] > numbers[r])
                l = m + 1;
            else if(numbers[m] < numbers[r])
                r = m;
            else
                r -= 1;
        }
        return numbers[l];
    }
}
// @lc code=end