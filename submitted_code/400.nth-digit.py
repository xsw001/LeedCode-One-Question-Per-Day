#
# @lc app=leetcode.cn id=400 lang=python
#
# [400] nth-digit
#
class Solution(object):
    def findNthDigit(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n < 10:
            return n
        a = 9
        b = 1
        end = 0
        st = 0
        while end < n:
            st = end+1
            end += a * b
            a *= 10
            b += 1
        #print(st,end,a/90,b-1)
        nn = (n-st)/(b-1)+a/90
        y = (n-st)%(b-1)
        #print(nn)
        return int(str(nn)[y])
# @lc code=end